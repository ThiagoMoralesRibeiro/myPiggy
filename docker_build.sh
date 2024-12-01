#!/bin/bash

#Enter the name of the service. Remember that the name must be the same as what you configured in your docker-compose.yml
SERVICE_NAME="backend"

if [ ! -f "docker-compose.yml" ]; then
  echo "Erro: docker-compose.yml não encontrado no diretório atual."
  exit 1
fi

echo "Parando e removendo o container do serviço $SERVICE_NAME..."
docker compose stop $SERVICE_NAME && docker compose rm -f $SERVICE_NAME


echo "Rebuildando e subindo o container do serviço $SERVICE_NAME..."
docker compose up -d --build $SERVICE_NAME


if [ $(docker ps -q -f name=$SERVICE_NAME | wc -l) -gt 0 ]; then
  echo "O serviço $SERVICE_NAME foi reiniciado com sucesso!"
else
  echo "Erro: O serviço $SERVICE_NAME não pôde ser iniciado. Verifique os logs."
  docker compose logs $SERVICE_NAME
fi
