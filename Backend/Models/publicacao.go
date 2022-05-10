package models

import "gorm.io/gorm"

type Publicacao struct {
	gorm.Model
	publicacao_id uint   `json:"publicacao_id" gorm:"primaryKey"`
	usuario_id    uint   `json:"usuario_id"`
	livro_id      uint   `json:"livro_id"`
	local         string `json:"local"`
	status_atual  string `json:"status_atual"`
}
