create table shipping_company(
        id serial UNIQUE,
        name varchar(50) NOT NULL unique,
        active boolean
)