O que é myPiggy?

myPiggy é uma aplicação web completa para gestão financeira pessoal, construída com as seguintes tecnologias:

    Frontend: React
    Backend: Java Spring Boot
    Banco de dados: PostgreSQL

Pré-requisitos

    Docker: https://docs.docker.com/get-docker/
    Docker Compose: Instalado junto com o Docker

Como executar o projeto localmente

    Clone o repositório:
    Bash

git clone https://seu-repositorio.git myPiggy
cd myPiggy

Construa as imagens Docker:
Bash

docker-compose build

Este comando irá construir as imagens Docker para o frontend, backend e banco de dados, baseando-se nos arquivos Dockerfile presentes nos respectivos diretórios.

Inicie os containers:
Bash

    docker-compose up -d

    O comando up iniciará os containers em modo detached (-d), permitindo que você continue utilizando o terminal.

Acesso à aplicação

    Frontend: Abra seu navegador e acesse http://localhost:3000.
    Backend: Para acessar a API, utilize um cliente HTTP como o Postman ou curl. A porta padrão para o backend é geralmente a 8080, mas verifique o seu arquivo docker-compose.yml para confirmar.

Estrutura do projeto

    frontend: Contém o código fonte da aplicação React.
    backend: Contém o código fonte da aplicação Spring Boot.
    docker-compose.yml: Define os serviços, redes e volumes utilizados pelo Docker Compose.

Configurando o banco de dados

    Inicialmente: O comando docker-compose up cria um container PostgreSQL com uma instância limpa do banco de dados.
    Migrações: Para aplicar as migrações do banco de dados, você pode adicionar um script ao seu docker-compose.yml ou executar um comando separado após iniciar os containers.
    Dados iniciais: Se precisar popular o banco com dados iniciais, adicione um script SQL ao seu projeto e execute-o após as migrações.

Personalização

    Variáveis de ambiente: Para personalizar a aplicação, você pode definir variáveis de ambiente no arquivo docker-compose.yml. Por exemplo, para alterar a porta do backend, você pode adicionar:
    YAML

    services:
      backend:
        ports:
          - "8081:8080"

    Arquivos de configuração: Você pode adicionar arquivos de configuração específicos para cada serviço (por exemplo, application.properties para o Spring Boot) e montá-los nos containers.

