create table users
(
    user_id      serial             not null,
    name         varchar(44)        not null,
    surname      varchar(44)        not null,
    email        varchar(70)        not null,
    username     varchar(46) unique not null,
    phone_number varchar(15)        not null,
    primary key (user_id)
);