package models

import "gorm.io/gorm"

type Usuarios struct {
	gorm.Model
	usuario_id      int    `json:"usuario_id" gorm:"primaryKey"`
	nome            string `json:"nome"`
	cpf             string `json:"cpf"`
	telefone        string `json:"telefone"`
	endereco        string `json:"endereco"`
	data_nascimento string `json:"data_nascimento"`
	senha           string `json:"senha"`
}
