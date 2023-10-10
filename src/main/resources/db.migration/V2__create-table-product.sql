create table
    product(
        id serial UNIQUE PRIMARY KEY,
        description varchar(255),
        name varchar(255) NOT NULL unique,
        active boolean
    );
