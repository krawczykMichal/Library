create table authors
(
    author_id serial      not null,
    name      varchar(64) not null,
    surname   varchar(64) not null,
    primary key (author_id)
);