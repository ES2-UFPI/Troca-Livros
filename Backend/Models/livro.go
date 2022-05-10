package models

import "gorm.io/gorm"

type Livro struct {
	gorm.Model
	Livro_id        uint   `json:"livro_id" gorm:"primaryKey"`
	Usuario_id      uint   `json:"usuario_id"`
	Titulo          string `json:"titulo"`
	Autor           string `json:"autor"`
	Data_publicacao string `json:"data_publicacao"`
	Editora         string `json:"editora"`
	Resumo          string `json:"resumo"`
	Categoria       string `json:"categoria"`
}
