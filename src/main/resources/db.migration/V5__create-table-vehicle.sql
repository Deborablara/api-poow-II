create table vehicle(
        id serial UNIQUE,
        plate_number varchar(20) PRIMARY KEY,
        shipping_company_id int,
        active boolean,
        FOREIGN KEY (shipping_company_id) REFERENCES shipping_company(id)
);

insert into vehicle(plate_number, shipping_company_id, active) values('5FDE52', 1, true)
insert into vehicle(plate_number, shipping_company_id, active) values('5FDE32', 2, true)
insert into vehicle(plate_number, shipping_company_id, active) values('5FDE12', 3, true)
insert into vehicle(plate_number, shipping_company_id, active) values('5FDE54', 2, true)