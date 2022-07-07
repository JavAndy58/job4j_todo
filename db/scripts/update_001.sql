CREATE TABLE IF NOT EXISTS users
(
  id SERIAL PRIMARY KEY,
  email VARCHAR NOT NULL,
  password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS items
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP,
    done BOOLEAN,
    user_id INT NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS categories
(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE items_categories
(
    item_id INT NOT NULL REFERENCES items(id),
    categories_id INT NOT NULL REFERENCES categories(id)
);

INSERT INTO categories (name) VALUES ('Работа');
INSERT INTO categories (name) VALUES ('Семья');
INSERT INTO categories (name) VALUES ('Образование');
INSERT INTO categories (name) VALUES ('Личное');