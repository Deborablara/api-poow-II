create table vehicle(
        id serial UNIQUE,
        plate_number varchar(20) PRIMARY KEY,
        shipping_company_id int,
        active boolean,
        FOREIGN KEY (shipping_company_id) REFERENCES shipping_company(id)
);