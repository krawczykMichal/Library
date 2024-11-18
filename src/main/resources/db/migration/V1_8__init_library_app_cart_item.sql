create table cart_item
(
    cart_item_id serial       not null,
    book_id      int          not null,
    title        varchar(100) not null,
    quantity     int          not null,
    primary key (cart_item_id),
    constraint fk_cart_item_books
        foreign key (book_id)
            references books (book_id)
)