package models

import "gorm.io/gorm"

type Transferencia struct {
	gorm.Model
	transferencia_id     uint   `json:"transferencia_id" gorm:"primaryKey"`
	publicacao_id        uint   `json:"publicacao_id"`
	interessado_id       uint   `json:"interessado_id"`
	livro_interessado_id uint   `json:"livro_interessado_id"`
	data_inicio          string `json:"data_inicio"`
	local_encontro       string `json:"local_encontro"`
	finalizado           bool   `json:"finalizado"`
}
