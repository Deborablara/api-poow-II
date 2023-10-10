create table tb_users_roles(
    user_id int NOT NULL,
    role_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    FOREIGN KEY (role_id) REFERENCES tb_role(role_id)
);


insert into tb_users_roles(user_id,role_id) values(1,1);
insert into tb_users_roles(user_id,role_id) values(2,1);
insert into tb_users_roles(user_id,role_id) values(3,3);