create table cart_item
(
    cart_item_id serial not null,
    cart_id      int    not null,
    book_id      int null,
    title        varchar(100) null,
    quantity     int default 0,
    primary key (cart_item_id),
    constraint fk_cart_item_books
        foreign key (book_id)
            references books (book_id),
    constraint fk_cart_item_cart
        foreign key (cart_id)
            references cart (cart_id)
)