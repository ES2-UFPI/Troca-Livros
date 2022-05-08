#!/bin/zsh

docker stop $(docker ps -aq);
docker rm $(docker ps -aq);
docker volume rm troca-livros_pg_data;

