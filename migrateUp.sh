#!/bin/sh
migrate -path Backend/Banco/migration -database "postgresql://root:root@localhost:5432/test_db?sslmode=disable" -verbose up

