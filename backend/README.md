Base URL

http://localhost:8080/account
GET /account

Descrição: Retorna a lista de todas as contas cadastradas.

Resposta:

[
    {
        "id": "dba87c3a-f664-4b5a-84b0-3e6e5a0c6151",
        "user": {
            "id": 8,
            "name": "Marcos Nunes",
            "email": "marq12@gmail.com"
        },
        "balanceInCents": 5000,
        "accountType": "BankAccount",
        "accountNumber": "1234567890",
        "branchNumber": "2"
    },
    {
        "id": "117696bf-f6f9-458d-ab0a-6818530e21e6",
        "user": {
            "id": 7,
            "name": "Mark Hamill",
            "email": "mk@example.com"
        },
        "balanceInCents": 10000,
        "accountType": "BankAccount",
        "accountNumber": "1234567893",
        "branchNumber": "004"
    }
]

GET /account/{accountNumber}

Descrição: Retorna os detalhes de uma conta específica pelo número da conta.

Parâmetro de URL:

    accountNumber: Número da conta.

Resposta:

{
    "id": "dba87c3a-f664-4b5a-84b0-3e6e5a0c6151",
    "user": {
        "id": 8,
        "name": "Marcos Nunes",
        "email": "marq12@gmail.com"
    },
    "balanceInCents": 5000,
    "accountType": "BankAccount",
    "accountNumber": "1234567890",
    "branchNumber": "2"
}

POST /account

Descrição: Cria uma nova conta.

Corpo da Requisição (JSON):

{
    "id": "117696bf-f6f9-458d-ab0a-6818530e21e6",
    "user": {
        "id": 7,
        "name": "Mark Hamill",
        "email": "mk@example.com"
    },
    "balanceInCents": 10000,
    "accountType": "BankAccount",
    "accountNumber": "1234567893",
    "branchNumber": "004"
}

Resposta:

"Account created successfully."

PUT /account

Descrição: Atualiza os detalhes de uma conta existente.

Corpo da Requisição (JSON):

{
    "id": "117696bf-f6f9-458d-ab0a-6818530e21e6",
    "user": {
        "id": 7,
        "name": "Mark Hamill",
        "email": "mk@example.com"
    },
    "balanceInCents": 12000,
    "accountType": "BankAccount",
    "accountNumber": "1234567893",
    "branchNumber": "004"
}

Resposta:

"Account updated successfully."

DELETE /account/{accountNumber}

Descrição: Remove uma conta pelo número.

Parâmetro de URL:

    accountNumber: Número da conta.

Resposta (Sucesso):

{
    "message": "Account deleted successfully."
}

Resposta (Erro):

{
    "error": "Failed to delete the account."
}
