create table books
(
    book_id        serial             not null,
    title          varchar(140)       not null,
    isbn           varchar(13) unique not null,
    publisher      varchar(120)       not null,
    author_id      int                not null,
    category_id    int                not null,
    published_date DATE,
    copies         int     default 1,
    available      boolean default true,
    primary key (book_id),
    constraint fk_library_books_author
        foreign key (author_id)
            references author (author_id),
    constraint fk_library_books_category
        foreign key (category_id)
            references category (category_id)
);