CREATE TABLE "Usuarios"(
  "usuario_id" int NOT NULL UNIQUE AUTO_INCREMENT,
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
  "livro_id" int NOT NULL UNIQUE AUTO_INCREMENT,
  "usuario_id" int,
  "titulo" varchar(255),
  "autor" varchar(255),
  "ano_publicacao" year,
  "editora" varchar(255),
  "resumo" varchar(255),
  "categoria" ENUM('Fantasia', 'Ficção científica', 'Distopia', 'Ação e aventura', 'Ficção   Policial', 'Horror', 'Thriller e Suspense', 'Ficção histórica', 'Romance', 'Ficção Feminina', 'LGBTQ+', 'Ficção Contemporânea', 'Realismo mágico', 'Graphic Novel', 'Conto', 'Young adult – Jovem adulto', 'New adult – Novo Adulto ', 'Infantil', 'Memórias e autobiografia', 'Biografia', 'Gastronomia', 'Arte e Fotografia', 'Autoajuda', 'História', 'Viajem', 'Crimes Reais', 'Humor', 'Ensaios', 'Guias & Como fazer ', 'Religião e Espiritualidade', 'Humanidades e Ciências Sociais', 'Paternidade e família', 'Tecnologia e Ciência', 'Infantil'),
  PRIMARY KEY ("livro_id"),
  FOREIGN KEY ("usuario_id") REFERENCES Usuarios("usuario_id")
);

CREATE TABLE Publicacoes(
  "publicacao_id" int NOT NULL UNIQUE AUTO_INCREMENT,
  "usuario_id" int,
  "livro_id" int,
  "local" varchar(255),
  "status" ENUM('ABERTA', 'CANCELADA', 'NEGOCIANDO', 'FINALIZADA'),
  PRIMARY KEY ("publicacao_id"),
  FOREIGN KEY ("usuario_id") REFERENCES Usuarios("usuario_id"),
  FOREIGN KEY ("livro_id") REFERENCES Livros("livro_id")
);

CREATE TABLE Transferencias(
  "transferencia_id" int NOT NULL UNIQUE AUTO_INCREMENT,
  "publicacao_id" int,
  "interessado_id" int,
  "livro_interessado_id" int,
  "data_inicio" datetime,
  "data_finalizacao" datetime,
  "local_encontro" varchar(255),
  "finalizado" boolean,
  PRIMARY KEY ("tranferencia_id"),
  FOREIGN KEY ("publicacao_id") REFERENCES Publicacoes("publicacao_id"),
  FOREIGN KEY ("interessado_id") REFERENCES Usuarios("usuario_id"),
  FOREIGN KEY ("livro_interessado_id") REFERENCES Livros("livro_id")
);

CREATE INDEX ON "Usuarios" ("cpf");
CREATE INDEX ON "Usuarios" ("email");
CREATE INDEX ON "Transferencias" ("transferencia_id");