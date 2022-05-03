package api

import (
	"errors"
	"net/http"

	"github.com/gin-gonic/gin"
)

type usuario struct {
	Id         string   `json:"id"`
	Nome       string   `json:"nome"`
	Cpf        string   `json:"cpf"`
	Telnumber  string   `json:"telnumber"`
	Email      string   `json:"email"`
	Endereço   string   `json:"endereço"`
	Nascimento string   `json:"nascimento"`
	Senha      string   `json:"senha"`
	Livros     []string `json:"livros"`
}

var users = []usuario{
	{
		Id: "1", Nome: "pedro", Cpf: "1", Telnumber: "23",
		Email: "mail", Endereço: "rua", Nascimento: "3",
		Senha: "123", Livros: []string{}},
}

func GetBooksFromUser(context *gin.Context) {

	userid := context.Param("id")
	user, err := GetUserbyId(userid)

	if err != nil {
		context.JSON(http.StatusNotFound, gin.H{"message": "User not found"})
		return
	}

	context.IndentedJSON(http.StatusOK, user.Livros)
}

func GetUserbyId(id string) (*usuario, error) {
	for i, u := range users {
		if u.Id == id {
			return &users[i], nil
		}
	}

	return nil, errors.New("User not found")
}

func GetUsers(context *gin.Context) {
	context.IndentedJSON(http.StatusOK, users)
}

func GetUser(context *gin.Context) {
	id := context.Param("id")
	user, err := GetUserbyId(id)

	if err != nil {
		context.JSON(http.StatusNotFound, gin.H{"message": "User not found"})
		return
	}

	context.IndentedJSON(http.StatusOK, user)
}

func AddUser(context *gin.Context) {
	var newUser usuario

	if err := context.BindJSON(&newUser); err != nil {
		return
	}
	users = append(users, newUser)

	context.JSON(http.StatusCreated, newUser)
}

func AddBook(context *gin.Context) {
	id := context.Param("id")
	name := context.Param("name")
	user, err := GetUserbyId(id)

	if err != nil {
		context.JSON(http.StatusNotFound, gin.H{"message": "User not found"})
		return
	}

	user.Livros = append(user.Livros, name)

	context.JSON(http.StatusCreated, id)

}

func DeleteBook(context *gin.Context) {
	id := context.Param("id")
	name := context.Param("name")
	user, err := GetUserbyId(id)

	if err != nil {
		context.JSON(http.StatusNotFound, gin.H{"message": "User not found"})
		return
	}

	for i := 0; i < len(user.Livros); i++ {
		if user.Livros[i] == name {
			user.Livros[i] = user.Livros[len(user.Livros)-1]
			user.Livros[len(user.Livros)-1] = ""
			user.Livros = user.Livros[:len(user.Livros)-1]

			context.JSON(http.StatusOK, gin.H{"message": "Entry deleted"})
			return
		}
	}
}

func Run() {
	router := gin.Default()
	router.GET("/users", GetUsers)
	router.GET("/users/:id", GetUser)
	router.GET("/books/:id", GetBooksFromUser)
	router.POST("/books/:id/:name", AddBook)
	router.POST("/users", AddUser)
	router.DELETE("/books/:id/:name", DeleteBook)
	router.Run("0.0.0.0:8080")
}
