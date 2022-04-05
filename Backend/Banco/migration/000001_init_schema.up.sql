CREATE TABLE "usuarios"(
    "id" bigserial PRIMARY KEY,
    "nome" varchar NOT NULL,
    "cpf" bigserial NOT NULL UNIQUE,
    "telnumber" bigint NOY NULL,
    "email" varchar NOT NULL UNIQUE,
    "endereço" varchar NOT NULL,
    "nascimento" date NOT NULL,
    "senha" varchar NOT NULL,
);

CREATE INDEX ON "accounts" ("cpf");
CREATE INDEX ON "accounts" ("email");