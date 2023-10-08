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