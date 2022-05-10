package models

import "gorm.io/gorm"

type Transferencia struct {
	gorm.Model
	Transferencia_id     uint   `json:"transferencia_id" gorm:"primaryKey"`
	Publicacao_id        uint   `json:"publicacao_id"`
	Interessado_id       uint   `json:"interessado_id"`
	Livro_interessado_id uint   `json:"livro_interessado_id"`
	Data_inicio          string `json:"data_inicio"`
	Local_encontro       string `json:"local_encontro"`
	Finalizado           bool   `json:"finalizado"`
}
