CREATE DATABASE bank;

USE bank;

CREATE TABLE accounts (
    account_id INT PRIMARY KEY,
    name VARCHAR(50),
    balance DOUBLE
);

INSERT INTO accounts VALUES 
(1, 'Alice', 1000.0),
(2, 'Bob', 1500.0);
