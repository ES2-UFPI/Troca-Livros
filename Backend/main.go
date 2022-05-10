package main

import (
	con "github.com/ES2-UFPI/Troca-Livros/Backend/Banco"
	api "github.com/ES2-UFPI/Troca-Livros/Backend/api"
)

func main() {

	con.GetDatabase()

	api.Run()
}
