create table category
(
    category_id serial              not null,
    name        varchar(100) unique not null,
    primary key (category_id)
);