package models

import "gorm.io/gorm"

type Publicacao struct {
	gorm.Model
	Publicacao_id uint   `json:"publicacao_id" gorm:"primaryKey"`
	Usuario_id    uint   `json:"usuario_id"`
	Livro_id      uint   `json:"livro_id"`
	Local         string `json:"local"`
	Status_atual  string `json:"status_atual"`
}
