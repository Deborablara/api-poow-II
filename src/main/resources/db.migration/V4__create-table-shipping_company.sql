create table shipping_company(
        id serial UNIQUE,
        name varchar(50) NOT NULL unique,
        active boolean
)


insert into shipping_company(name) values('correios', true);
insert into shipping_company(name) values('sedex', true);
insert into shipping_company(name) values('99', true);