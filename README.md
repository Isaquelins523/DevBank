Banco API - Backend
Descrição

Este projeto é a implementação de uma API RESTful para gerenciamento de contas bancárias, permitindo a criação de contas, depósitos, saques, transferências e consulta de saldo. A API foi construída utilizando Spring Boot e JPA com um banco de dados PostgreSQL.
Funcionalidades

    Criar conta bancária
    Depositar dinheiro
    Sacar dinheiro
    Transferir dinheiro entre contas
    Consultar saldo de conta

Tecnologias Utilizadas

    Java 17
    Spring Boot 3.x
    JPA (Hibernate)
    PostgreSQL
    Lombok
    Docker (opcional para execução do PostgreSQL)
    JUnit para testes unitários

Estrutura do Projeto

O projeto está dividido nas seguintes camadas:

    Controller: Define os endpoints da API.
    Service: Lógica de negócios (operações sobre as contas bancárias).
    Repository: Interações com o banco de dados.
    Requests: Classes para as requisições HTTP.

Endpoints
1. Criar Conta Bancária

POST /accounts

Cria uma nova conta bancária com o número gerado automaticamente.

    Request Body:

{
"typeAccount": "SAVINGS",
"clientId": "12345"
}

Resposta:

    {
      "accountId": "UUID",
      "numberAccount": "0000012345",
      "balance": 0.00,
      "typeAccount": "SAVINGS",
      "clientId": "12345"
    }

2. Depositar Dinheiro

POST /transactions/deposit

Realiza um depósito em uma conta bancária.

    Request Body:

{
"accountId": "UUID",
"amount": 1000
}

Resposta:

    {
      "accountId": "UUID",
      "balance": 1000
    }

3. Sacar Dinheiro

POST /transactions/withdraw

Realiza um saque de uma conta bancária.

    Request Body:

{
"accountId": "UUID",
"amount": 500
}

Resposta:

    {
      "accountId": "UUID",
      "balance": 500
    }

4. Transferir Dinheiro

POST /transactions/transfer

Realiza uma transferência entre duas contas bancárias.

    Request Body:

{
"fromAccountId": "UUID",
"toAccountId": "UUID",
"amount": 200
}

Resposta:

    {
      "message": "Transfer of 200.00 done successfully!"
    }

5. Consultar Saldo

GET /accounts/{accountId}/balance

Consulta o saldo de uma conta bancária.

    Resposta:

    {
      "accountId": "UUID",
      "balance": 5000
    }


Execução do Projeto
Pré-requisitos

    Java 17 ou superior
    Maven 3.x
    PostgreSQL
    Docker (opcional)

Como Rodar

    Rodando o projeto localmente:
        Clone este repositório.
        Navegue até o diretório do projeto e execute:

    mvn spring-boot:run

Rodando o PostgreSQL via Docker (opcional):

Se você deseja rodar o PostgreSQL em um container Docker, execute:

    docker run --name bank-db -e POSTGRES_PASSWORD=yourpassword -p 5432:5432 -d postgres

    Configuração de banco de dados:
        Altere a configuração do application.properties ou application.yml para conectar-se ao seu banco de dados PostgreSQL.

Testando a API

Você pode usar ferramentas como Postman ou Insomnia para testar os endpoints da API.