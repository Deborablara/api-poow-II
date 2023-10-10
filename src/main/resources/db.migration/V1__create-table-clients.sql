create table client(
    id serial UNIQUE PRIMARY KEY,
    name varchar(50) NOT NULL unique,
    active boolean
);


insert into client(name,active) values('Kevin', true)
insert into client(name,active) values('Ana', true)
insert into client(name,active) values('Julia', true)
insert into client(name,active) values('Debora', true)