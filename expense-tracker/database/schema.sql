CREATE DATABASE IF NOT EXISTS expense_tracker;
USE expense_tracker;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    amount DOUBLE NOT NULL,
    category VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    date DATE NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_transaction_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);
