create table users
(
    id serial primary key,
    email varchar (2000),
    password varchar (2000)
);

create table items
(
    id serial primary key,
    name text,
    description text,
    created timestamp,
    done boolean,
    user_id int not null references users(id)
);

create table categories
(
    id serial primary key,
    name text
);

create table items_categories
(
    items_id serial primary key,
    categories_id serial primary key
)

INSERT INTO categories(name) VALUES ('Работа');
INSERT INTO categories(name) VALUES ('Семья');
INSERT INTO categories(name) VALUES ('Образование');
INSERT INTO categories(name) VALUES ('Личное');