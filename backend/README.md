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
    },
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

