package models

import "gorm.io/gorm"

type Usuario struct {
	gorm.Model
	Usuario_id      uint   `json:"usuario_id" gorm:"primaryKey"`
	Nome            string `json:"nome"`
	Cpf             string `json:"cpf"`
	Telefone        string `json:"telefone"`
	Endereco        string `json:"endereco"`
	Data_nascimento string `json:"data_nascimento"`
	Senha           string `json:"senha"`
}
