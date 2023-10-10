create table
    tb_user(
        user_id serial UNIQUE PRIMARY KEY,
        user_name varchar(50) NOT NULL,
        password varchar(100) NOT NULL,
        token varchar(255),
    );
