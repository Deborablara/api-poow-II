create table client(
    id serial UNIQUE PRIMARY KEY,
    name varchar(50) NOT NULL unique,
    active boolean
);