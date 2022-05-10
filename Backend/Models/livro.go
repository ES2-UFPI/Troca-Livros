package models

import "gorm.io/gorm"

type Livro struct {
	gorm.Model
	livro_id        uint   `json:"livro_id" gorm:"primaryKey"`
	usuario_id      uint   `json:"usuario_id"`
	titulo          string `json:"titulo"`
	autor           string `json:"autor"`
	data_publicacao string `json:"data_publicacao"`
	editora         string `json:"editora"`
	resumo          string `json:"resumo"`
	categoria       string `json:"categoria`
}
