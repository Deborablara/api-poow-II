create table tb_user(
    user_id serial UNIQUE PRIMARY KEY,
    user_name varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    token varchar(255),
);


insert into tb_user(user_name, password) values('kevin', '12345678');
insert into tb_user(user_name, password) values('jorge', '12345678');
insert into tb_user(user_name, password) values('debora', '12345678');

