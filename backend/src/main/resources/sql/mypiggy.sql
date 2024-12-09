-- Database: myPiggy

-- DROP DATABASE IF EXISTS "myPiggy";

CREATE DATABASE "myPiggy"
    WITH
    OWNER = myuser
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Database: myPiggy

-- DROP DATABASE IF EXISTS "myPiggy";

CREATE DATABASE "myPiggy"
    WITH
    OWNER = myuser
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE users (
    id SERIAL PRIMARY KEY, 						-- Id do usuario, (possivelmente vai virar um UUID, no futuro) 
    name VARCHAR(100) NOT NULL, 				-- Nome do usuario
    email VARCHAR(100) UNIQUE NOT NULL, 		-- Email do usuario
    password VARCHAR(100) NOT NULL, 			-- Senha do usuario
	birth_date DATE NOT NULL, 					-- Data de nascimento
	phone_number VARCHAR(20) NOT NULL, 			-- Numero de telefone
	cpf VARCHAR(11) NOT NULL, 					-- CPF do cliente
	cep VARCHAR(8) NOT NULL, 				-- CEP do usuario
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

DROP TABLE IF EXISTS users CASCADE;


CREATE TABLE account (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),             -- Identificador único da conta
    user_id INT NOT NULL,                                       -- Referência ao usuário
    balance_in_cents INT NOT NULL DEFAULT 0,                    -- Saldo da conta em centavos
    account_type VARCHAR(50) NOT NULL,                          -- Tipo de conta geral (BankAccount, PiggyBank, etc)
    account_number VARCHAR(20) NOT NULL,                        -- Número da conta
    branch_number VARCHAR(10) NOT NULL,                         -- Número da agência
    CONSTRAINT fk_user FOREIGN KEY (user_id)                   -- Relacionamento com o usuário
        REFERENCES users(id)                                   -- Chave estrangeira que faz referência à tabela 'users'
        ON DELETE CASCADE                                      -- Quando um usuário for excluído, as contas associadas também serão excluídas
);


CREATE TABLE categories (
    id SERIAL PRIMARY KEY, 										--Identificadoor unico das Categorias
    name VARCHAR(50) NOT NULL, 						-- Nome da categoria, ex.: 'Alimentação', 'Transporte', 'Outros'
    description TEXT            						-- Descrição opcional da categoria
);


CREATE TABLE piggy_bank (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(), 			-- Identificador unico do porquinho
    user_id INT NOT NULL,                           			-- Referncia ao usuario
    goal_in_cents INTEGER NOT NULL DEFAULT 0,    				-- Meta do porco
    description TEXT,                               			-- Descricao do porquinho
    begin_date TIMESTAMP NOT NULL DEFAULT NOW(),    			-- Data em que ele comeca a estar ativo
    end_date TIMESTAMP,                             			-- Data de encerramento
    category_id INT,                                			-- Categoria associada ao piggy bank
    CONSTRAINT fk_user FOREIGN KEY (user_id)      
        REFERENCES Users(id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) 
        REFERENCES Categories(id)         
);

CREATE TYPE transaction_type AS ENUM ('debit', 'credit', 'investments', 'internal');


CREATE TABLE transactions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id UUID NOT NULL,
    category_id INT NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    amount_in_cents INT NOT NULL,
    description TEXT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_recurring BOOLEAN DEFAULT FALSE,
    recurrence_period VARCHAR(20),
    FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL
);


INSERT INTO users (
    name, email, password, birth_date, phone_number, cpf, address
)
VALUES (
    'João Silva',                               -- Nome do usuário
    'joao.silva@example.com',                   -- Email do usuário
    'senha123',                                 -- Senha do usuário (geralmente deve ser criptografada em uma aplicação real)
    '1990-01-01',                               -- Data de nascimento
    '(11) 91234-5678',                          -- Número de telefone
    '12345678901',                              -- CPF do cliente
    'Rua Exemplo, 123, São Paulo, SP'          -- Endereço do usuário
);

-- Inserindo uma conta para o usuário com id = 1
INSERT INTO account (user_id, balance_in_cents, account_type, account_number, branch_number)
VALUES (
    1,                          -- user_id, deve existir na tabela Users
    10000,                       -- saldo da conta em centavos (100,00)
    'BankAccount',               -- tipo da conta
    '1234567890',                -- número da conta
    '001'                        -- número da agência
);

SELECT * FROM account;

DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS account CASCADE;
