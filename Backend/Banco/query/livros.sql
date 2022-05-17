-- name: CreateLivro :one
INSERT INTO "Livros" (
    usuario_id,
    titulo,
    autor,
    data_publicacao,
    editora,
    resumo,
    categoria
) VALUES (
    $1, $2, $3, $4, $5, $6, $7
) RETURNING *;

-- name: GetLivro :one
SELECT * FROM "Livros"
WHERE livro_id = $1 LIMIT 1;

-- name: ListLivrosByUser :many
SELECT * FROM "Livros"
WHERE usuario_id = $1
ORDER BY titulo;

-- name: UpdateOwner :exec
UPDATE "Livros"
SET usuario_id = $2
WHERE livro_id = $1;