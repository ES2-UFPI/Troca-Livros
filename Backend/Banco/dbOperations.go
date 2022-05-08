package banco

import (
	"database/sql"
	"fmt"

	_ "github.com/lib/pq"
)

const (
	host     = "livros-postgres"
	port     = 5432
	user     = "root"
	password = "root"
	dbname   = "test_db"
)

var (
	DB *sql.DB
)

func Conn() {

	psqlInfo := fmt.Sprintf("host=%s port=%d user=%s "+
		"password=%s dbname=%s sslmode=disable",
		host, port, user, password, dbname)

	db, err := sql.Open("postgres", psqlInfo)
	if err != nil {
		panic(err)
	}

	defer db.Close()

	err = db.Ping()
	if err != nil {
		panic(err)
	}

	fmt.Println("Successfully Connected to the DB!")

}

func InsertUsuariosDO(nome, cpf, telefone, email, endereco, data_nascimento, senha string) {
	sqlStatement := `
	INSERT INTO Usuarios (nome, cpf, telefone, email, endereco, data_nascimento, senha)
	VALUES ($1,$2,$3,$4,$5,$6,$7)
	RETURNING usuario_id`
	id := 0
	err := DB.QueryRow(sqlStatement, nome, cpf, telefone, email, endereco, data_nascimento, senha).Scan(&id)
	if err != nil {
		panic(err)
	}
	fmt.Println("New record ID is:", id)
}

func InsertLivrosDO(usuario_id int, titulo, autor, data_publicacao, editora, resumo, categoria string) {
	sqlStatement := `
	INSERT INTO Livros (usuario_id, titulo, autor, data_publicacao, editora, resumo, categoria)
	VALUES ($1,$2,$3,$4,$5,$6,$7)
	RETURNING livro_id`
	id := 0
	err := DB.QueryRow(sqlStatement, usuario_id, titulo, autor, data_publicacao, editora, resumo, categoria).Scan(&id)
	if err != nil {
		panic(err)
	}
	fmt.Println("New record ID is:", id)
}

func InsertPublicacoesDO(usuario_id, livro_id int, local, status_atual string) {
	sqlStatement := `
	INSERT INTO Publicacoes (usuario_id, livro_id, local, status_atual)
	VALUES ($1,$2,$3,$4)
	RETURNING publicacao_id`
	id := 0
	err := DB.QueryRow(sqlStatement, usuario_id, livro_id, local, status_atual).Scan(&id)
	if err != nil {
		panic(err)
	}
	fmt.Println("New record ID is:", id)
}
