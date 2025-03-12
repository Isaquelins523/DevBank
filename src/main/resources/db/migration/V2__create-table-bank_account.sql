CREATE TABLE bank_account (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    numberAccount TEXT UNIQUE NOT NULL,
    balance DECIMAL(15,2) NOT NULL,
    typeAccount TEXT NOT NULL,
    client_id TEXT UNIQUE,
    FOREIGN KEY (client_id) REFERENCES clients(id),
    version BIGINT
    )