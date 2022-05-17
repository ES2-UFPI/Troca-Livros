package db

import (
	"context"
	"testing"
	"time"

	"github.com/stretchr/testify/require"
)

func createTestUser(t *testing.T) Usuario {
	arg := CreateUserParams{
		Nome:           "asdf",
		Cpf:            "asdf",
		Telefone:       "asdf",
		Email:          "asdf",
		Endereco:       "asdf",
		DataNascimento: time.Now(),
		Senha:          123,
	}

	user, err := testQueries.CreateUser(context.Background(), arg)

	require.NoError(t, err)
	require.NotEmpty(t, user)

	require.Equal(t, arg.Nome, user.Nome)
	require.Equal(t, arg.Cpf, user.Cpf)
	require.Equal(t, arg.Senha, user.Senha)
	require.NotZero(t, user.UsuarioID)

	return user
}

func TestCreateUser(t *testing.T) {
	createTestUser(t)
}

func TestGetUser(t *testing.T) {

	user1 := createTestUser(t)

	user2, err := testQueries.GetUser(context.Background(), user1.UsuarioID)

	require.NoError(t, err)
	require.NotEmpty(t, user2)

	require.Equal(t, user1.UsuarioID, user2.UsuarioID)
	require.Equal(t, user1.Cpf, user2.Cpf)
	require.Equal(t, user1.Senha, user2.Senha)
	require.Equal(t, user1.Nome, user2.Nome)
}
