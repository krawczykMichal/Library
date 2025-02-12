create table reservation_item
(
    reservation_item_id serial not null,
    reservation_id      int    not null,
    book_id             int null,
    title               varchar(100) null,
    quantity            int default 0,
    primary key (reservation_item_id),
    constraint fk_reservation_item_books
        foreign key (book_id)
            references books (book_id),
    constraint fk_reservation_item_reservations
        foreign key (reservation_id)
            references reservations (reservation_id)
)