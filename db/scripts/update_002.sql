CREATE TABLE IF NOT EXISTS categories
(
    id SERIAL PRIMARY KEY,
    name TEXT
);

INSERT INTO categories (name) VALUES ('Работа');
INSERT INTO categories (name) VALUES ('Семья');
INSERT INTO categories (name) VALUES ('Образование');
INSERT INTO categories (name) VALUES ('Личное');