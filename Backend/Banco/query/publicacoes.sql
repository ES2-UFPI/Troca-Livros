-- name: CreatePublicacao :one
INSERT INTO "Publicacoes" (
    usuario_id,
    livro_id,
    "local",
    status_atual
) VALUES (
    $1, $2, $3, $4
) RETURNING *;

-- name: GetPublicacao :one
SELECT * FROM "Publicacoes"
WHERE publicacao_id = $1 LIMIT 1;

-- name: ListPublicacoesByUser :many
SELECT * FROM "Publicacoes"
WHERE usuario_id = $1
ORDER BY publicacao_id;

-- name: UpdatePubStatus :exec
UPDATE "Publicacoes"
SET status_atual = $2
WHERE publicacao_id = $1;