create table employees
(
    employee_id     serial      not null,
    name            varchar(32) not null,
    surname         varchar(32) not null,
    email           varchar(64) not null,
    username        varchar(32) not null unique,
    employee_number varchar(10) not null,
    primary key (employee_id)
);