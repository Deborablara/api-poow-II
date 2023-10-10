create table tb_role(
    role_id serial UNIQUE PRIMARY KEY,
    role_name varchar(50) NOT NULL unique
);

insert into tb_role(role_name) values('ADMIN_EMPLOYEE')
insert into tb_role(role_name) values('ADMIN')
insert into tb_role(role_name) values('EMPLOYEE')



