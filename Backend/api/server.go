package api

import (
	"errors"
	"net/http"

	"github.com/gin-gonic/gin"
)

type usuario struct {
	Id         string `json:"id"`
	Nome       string `json:"nome"`
	Cpf        string `json:"cpf"`
	Telnumber  string `json:"telnumber"`
	Email      string `json:"email"`
	Endereço   string `json:"endereço"`
	Nascimento string `json:"nascimento"`
	Senha      string `json:"senha"`
}

var users = []usuario{
	{Id: "1", Nome: "pedro", Cpf: "1", Telnumber: "23", Email: "mail", Endereço: "rua", Nascimento: "3", Senha: "123"},
}

func getUserbyId(id string) (*usuario, error) {
	for i, u := range users {
		if u.Id == id {
			return &users[i], nil
		}
	}

	return nil, errors.New("User not found")
}

func getUsers(context *gin.Context) {
	context.JSON(http.StatusOK, users)
}

func getUser(context *gin.Context) {
	id := context.Param("id")
	user, err := getUserbyId(id)

	if err != nil {
		context.JSON(http.StatusNotFound, gin.H{"message": "User not found"})
		return
	}

	context.JSON(http.StatusOK, user)
}

func addUser(context *gin.Context) {
	var newUser usuario

	if err := context.BindJSON(&newUser); err != nil {
		return
	}
	users = append(users, newUser)

	context.JSON(http.StatusCreated, newUser)
}

func Run() {
	router := gin.Default()
	router.GET("/users", getUsers)
	router.GET("/users/:id", getUser)
	router.POST("/users", addUser)
	router.Run("0.0.0.0:8080")
}
