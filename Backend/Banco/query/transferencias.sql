-- name: CreateTransferencia :one
INSERT INTO "Transferencias" (
    publicacao_id,
    interessado_id,
    livro_interessado_id,
    data_inicio,
    data_finalizacao,
    local_encontro,
    finalizado
) VALUES (
    $1, $2, $3, $4, $5, $6, $7
) RETURNING *;

-- name: GetTransferencia :one
SELECT * FROM "Transferencias"
WHERE transferencia_id = $1 LIMIT 1;

-- name: ListTransferenciasByUser :many
SELECT * FROM "Transferencias"
WHERE interessado_id = $1
ORDER BY transferencia_id;