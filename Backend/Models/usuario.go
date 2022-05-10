package models

import "gorm.io/gorm"

type Usuario struct {
	gorm.Model
	usuario_id      uint   `json:"usuario_id" gorm:"primaryKey"`
	nome            string `json:"nome"`
	cpf             string `json:"cpf"`
	telefone        string `json:"telefone"`
	endereco        string `json:"endereco"`
	data_nascimento string `json:"data_nascimento"`
	senha           string `json:"senha"`
}
