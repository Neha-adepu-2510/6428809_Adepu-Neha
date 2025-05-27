CREATE DATABASE college;

USE college;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);

INSERT INTO students (id, name, age) VALUES
(1, 'Neha', 20),
(2, 'Hruthwik', 21),
(3, 'Aarav', 22);
