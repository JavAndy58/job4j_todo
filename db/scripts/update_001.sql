create table items (
    id serial primary key,
    name text,
    description text,
    created timestamp,
    done boolean,
    user_id int not null references users(id)
);

create table users (
    id serial primary key,
    email varchar (2000),
    password varchar (2000)
);
ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);