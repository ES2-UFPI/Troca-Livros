CREATE TYPE "categorias" AS ENUM('Fantasia', 'Ficção científica', 'Distopia', 'Ação e aventura', 'Ficção Policial', 'Horror', 'Thriller e Suspense', 'Ficção histórica', 'Romance', 'Ficção Feminina', 'LGBTQ+', 'Ficção Contemporânea', 'Realismo mágico', 'Graphic Novel', 'Conto', 'Jovem adulto', 'Novo Adulto', 'Infantil', 'Memórias e autobiografia', 'Biografia', 'Gastronomia', 'Arte e Fotografia', 'Autoajuda', 'História', 'Viajem', 'Crimes Reais', 'Humor', 'Ensaios', 'Guias & Como fazer', 'Religião e Espiritualidade', 'Humanidades e Ciências Sociais', 'Paternidade e família', 'Tecnologia e Ciência');
CREATE TYPE "status" AS ENUM('ABERTA', 'CANCELADA', 'NEGOCIANDO', 'FINALIZADA');

CREATE TABLE "Usuarios"(
  "usuario_id" SERIAL,
  "nome" varchar(255) NOT NULL,
  "cpf" varchar(255) NOT NULL UNIQUE,
  "telefone" varchar(255) NOT NULL,
  "email" varchar(255) NOT NULL UNIQUE,
  "endereco" varchar(255) NOT NULL,
  "data_nascimento" date NOT NULL,
  "senha" smallint NOT NULL,
  PRIMARY KEY ("usuario_id")
);

CREATE TABLE "Livros"(
  "livro_id" SERIAL,
  "usuario_id" integer,
  "titulo" varchar(255),
  "autor" varchar(255),
  "data_publicacao" date,
  "editora" varchar(255),
  "resumo" varchar(255),
  "categoria" "categorias",
  PRIMARY KEY ("livro_id"),
  FOREIGN KEY ("usuario_id") REFERENCES "Usuarios"("usuario_id")
);

CREATE TABLE "Publicacoes"(
  "publicacao_id" SERIAL,
  "usuario_id" integer,
  "livro_id" integer,
  "local" varchar(255),
  "status_atual" "status",
  PRIMARY KEY ("publicacao_id"),
  FOREIGN KEY ("usuario_id") REFERENCES "Usuarios"("usuario_id"),
  FOREIGN KEY ("livro_id") REFERENCES "Livros"("livro_id")
);

CREATE TABLE "Transferencias"(
  "transferencia_id" SERIAL,
  "publicacao_id" integer,
  "interessado_id" integer,
  "livro_interessado_id" integer,
  "data_inicio" date,
  "data_finalizacao" date,
  "local_encontro" varchar(255),
  "finalizado" boolean,
  PRIMARY KEY ("transferencia_id"),
  FOREIGN KEY ("publicacao_id") REFERENCES "Publicacoes"("publicacao_id"),
  FOREIGN KEY ("interessado_id") REFERENCES "Usuarios"("usuario_id"),
  FOREIGN KEY ("livro_interessado_id") REFERENCES "Livros"("livro_id")
);

CREATE INDEX ON "Usuarios" ("cpf");
CREATE INDEX ON "Usuarios" ("email");
CREATE INDEX ON "Transferencias" ("transferencia_id");