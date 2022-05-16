-- name: CreateUser :one
INSERT INTO "Usuarios" (
    nome,
    cpf,
    telefone,
    email,
    endereco,
    data_nascimento,
    senha
) VALUES (
    $1, $2, $3, $4, $5, $6, $7
) RETURNING *;


-- name: GetUser :one
SELECT * FROM "Usuarios"
WHERE usuario_id = $1 LIMIT 1;

-- name: GetUserbyName :many
SELECT * FROM "Usuarios"
WHERE nome = $1
OFFSET $2;

-- name: ListUsers :many
SELECT * FROM "Usuarios"
ORDER BY usuario_id
LIMIT $1
OFFSET $2;