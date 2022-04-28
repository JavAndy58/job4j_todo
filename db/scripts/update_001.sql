create table items (
    id serial primary key,
    description text,
    created timestamp without time zone not null,
    done boolean
);