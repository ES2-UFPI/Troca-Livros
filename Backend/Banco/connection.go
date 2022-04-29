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
