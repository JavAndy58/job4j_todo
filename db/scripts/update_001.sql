create table items (
    id serial primary key,
    description text,
    created timestamp without time zone not null default now(),
    done boolean
);