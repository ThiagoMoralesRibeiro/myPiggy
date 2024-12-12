# MyPiggy API

Bem-vindo à documentação da MyPiggy API. Esta API gerencia as funcionalidades/consumo de dados no sistema MyPiggy.

## Endpoints

### Listar todas as contas

**GET** `/account`

Retorna a lista de todas as contas.

#### Respostas:
- **200**: Lista de contas retornada com sucesso.

#### Exemplo de resposta:
```json
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
```

### Criar uma nova conta

**POST** `/account`

Cria uma nova conta no sistema.

#### Body da requisição:
```json
{
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
```

#### Respostas:
- **201**: Conta criada com sucesso.
- **400**: Formato JSON inválido.
- **500**: Erro interno do servidor.

### Atualizar uma conta existente

**PUT** `/account`

Atualiza as informações de uma conta.

#### Body da requisição:
```json
{
  "id": "dba87c3a-f664-4b5a-84b0-3e6e5a0c6151",
  "balanceInCents": 10000
}
```

#### Respostas:
- **200**: Conta atualizada com sucesso.
- **400**: Formato JSON inválido.
- **500**: Erro interno do servidor.

### Buscar uma conta pelo número

**GET** `/account/{accountNumber}`

Busca as informações de uma conta específica pelo número.

#### Parâmetros:
- `accountNumber` (string): Número da conta a ser buscada.

#### Respostas:
- **200**: Detalhes da conta retornados com sucesso.
- **404**: Conta não encontrada.

### Deletar uma conta

**DELETE** `/account/{accountNumber}`

Remove uma conta específica pelo número.

#### Parâmetros:
- `accountNumber` (string): Número da conta a ser removida.

#### Respostas:
- **200**: Conta removida com sucesso.
- **404**: Conta não encontrada.



### `GET /transaction`

Retorna uma lista de todas as transações.

**Exemplo de Requisição:**

```
GET /transaction HTTP/1.1
Host: api.exemplo.com
```

**Resposta de Sucesso:**

```json
[
    {
        "id": "ea1c5090-590a-4725-a19a-070238bff764",
        "account": {
            "balanceInCents": 5000,
            "accountType": "BankAccount",
            "accountNumber": "1234567890",
            "branchNumber": "2"
        },
        "transactionType": "debit",
        "amountInCents": 8000,
        "description": "Teste de transacao",
        "transactionDate": {
            "year": 2024,
            "month": 1,
            "day": 2
        },
        "category": {
            "id": 3,
            "name": "Outros",
            "description": "Despesas diversas que não se enquadram em outras categorias"
        },
        "isRecurring": false
    }
]
```

### `GET /transaction/{id}`

Retorna os detalhes de uma transação especificada pelo ID.

**Exemplo de Requisição:**

```
GET /transaction/ea1c5090-590a-4725-a19a-070238bff764 HTTP/1.1
Host: api.exemplo.com
```

**Resposta de Sucesso:**

```json
{
    "id": "ea1c5090-590a-4725-a19a-070238bff764",
    "account": {
        "balanceInCents": 5000,
        "accountType": "BankAccount",
        "accountNumber": "1234567890",
        "branchNumber": "2"
    },
    "transactionType": "debit",
    "amountInCents": 8000,
    "description": "Teste de transacao",
    "transactionDate": {
        "year": 2024,
        "month": 1,
        "day": 2
    },
    "category": {
        "id": 3,
        "name": "Outros",
        "description": "Despesas diversas que não se enquadram em outras categorias"
    },
    "isRecurring": false
}
```

### `POST /transaction`

Cria uma nova transação.

**Exemplo de Requisição:**

```
POST /transaction HTTP/1.1
Host: api.exemplo.com
Content-Type: application/json

{
    "account": {
        "balanceInCents": 10000,
        "accountType": "BankAccount",
        "accountNumber": "1234567893",
        "branchNumber": "004"
    },
    "transactionType": "credit",
    "amountInCents": 2000,
    "description": "Depósito",
    "transactionDate": {
        "year": 2024,
        "month": 11,
        "day": 11
    },
    "category": {
        "id": 3,
        "name": "Outros",
        "description": "Despesas diversas que não se enquadram em outras categorias"
    },
    "isRecurring": false
}
```

**Resposta de Sucesso:**

```json
{
    "id": "novo-id-gerado",
    "message": "Transação criada com sucesso."
}
```

### `PUT /transaction/{id}`

Atualiza uma transação existente.

**Exemplo de Requisição:**

```
PUT /transaction/ea1c5090-590a-4725-a19a-070238bff764 HTTP/1.1
Host: api.exemplo.com
Content-Type: application/json

{
    "description": "Compra de supermercado",
    "amountInCents": 12000
}
```

**Resposta de Sucesso:**

```json
{
    "message": "Transação atualizada com sucesso."
}
```

### `DELETE /transaction/{id}`

Remove uma transação pelo ID.

**Exemplo de Requisição:**

```
DELETE /transaction/ea1c5090-590a-4725-a19a-070238bff764 HTTP/1.1
Host: api.exemplo.com
```

**Resposta de Sucesso:**

```json
{
    "message": "Transação removida com sucesso."
}
```


### 1. **GET /user**

Obtém a lista de todos os usuários.

#### Exemplo de resposta:
```json
[
    {
        "id": 6,
        "name": "João Silva",
        "email": "joao.silva@example.com",
        "password": "password123",
        "birthDate": {
            "year": 1990,
            "month": 5,
            "day": 20
        },
        "phoneNumber": "11987654321",
        "cpf": "12345678901",
        "cep": "01001000"
    },
    {
        "id": 7,
        "name": "Mark Hamill",
        "email": "mk@example.com",
        "password": "$2b$10$MCYKEbjqY2fn4KEno1Ii/uj1ZZk2t00DDzoYAW.hd7UK7U7cg1yT.",
        "birthDate": {
            "year": 2000,
            "month": 5,
            "day": 21
        },
        "phoneNumber": "119876597895",
        "cpf": "12365478965",
        "cep": "88704079"
    },
    {
        "id": 8,
        "name": "Marcos Nunes",
        "email": "marq12@gmail.com",
        "password": "$2b$10$shUJvCLizt96Tyux5El9nOYl0kF/TvZRTUxPM2FW41Tjj91sADZ1e",
        "birthDate": {
            "year": 2024,
            "month": 5,
            "day": 21
        },
        "phoneNumber": "11987654726",
        "cpf": "12365478900",
        "cep": "07115000"
    }
]



## Esquema dos objetos

### User
```json
{
        "id": 6,
        "name": "João Silva",
        "email": "joao.silva@example.com",
        "password": "password123",
        "birthDate": {
            "year": 1990,
            "month": 5,
            "day": 20
        },
        "phoneNumber": "11987654321",
        "cpf": "12345678901",
        "cep": "01001000"
}
```

### Account
```json
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
```

### Transaction
```json
{
  "id": "37f402f0-921d-4659-8fe1-1f4380c26bf6",
  "account": {
    "balanceInCents": 5000,
    "accountType": "BankAccount",
    "accountNumber": "1234567890",
    "branchNumber": "2"
  },
  "transactionType": "credit",
  "amountInCents": 8000,
  "description": "Teste de transacao",
  "transactionDate": {
    "year": 2024,
    "month": 1,
    "day": 2
  },
  "category": {
    "id": 3,
    "name": "Outros",
    "description": "Despesas diversas que não se enquadram em outras categorias"
  },
  "isRecurring": false
}

```
### Category
```json
[
    {
        "id": 3,
        "name": "Outros",
        "description": "Despesas diversas que não se enquadram em outras categorias"
    },
    {
        "id": 1,
        "name": "Alimentação",
        "description": "Despesas com alimentos, refeicoes e outros"
    },
    {
        "id": 4,
        "name": "Investimentos",
        "description": "Valor adicionado a carteira de investimento"
    }
]
```

