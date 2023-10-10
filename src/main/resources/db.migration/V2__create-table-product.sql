create table product(
    id serial UNIQUE PRIMARY KEY,
    description varchar(255),
    name varchar(255) NOT NULL unique,
    active boolean
);


insert into product(description, name, active) values('Ração canina', 'Atacama', true)
insert into product(description, name, active) values('Ração canina', 'Biofresh.', true)
insert into product(description, name, active) values('Ração canina', 'Bonny', true)
insert into product(description, name, active) values('Ração canina', 'Cibau', true)