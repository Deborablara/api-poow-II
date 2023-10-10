create table request (
    id serial UNIQUE PRIMARY KEY,
    amount float NOT NULL,
    status varchar not null,
    id_client int NOT NULL,
    id_product int NOT NULL,
    id_vehicle int NOT NULL,
    FOREIGN KEY (id_client) REFERENCES client (id),
    FOREIGN KEY (id_product) REFERENCES product (id),
    FOREIGN KEY (id_vehicle) REFERENCES vehicle(id)
);

insert into request(amount,status,id_client,id_product,id_vehicle) values(5000.00, 'IN_PRODUCTION', 1, 1, 1)
insert into request(amount,status,id_client,id_product,id_vehicle) values(2500.00, 'WAITING_FOR_PRODUCTION', 2, 1, 2)
insert into request(amount,status,id_client,id_product,id_vehicle) values(5000.00, 'WAITING_VEHICLE', 3, 1, 2)



