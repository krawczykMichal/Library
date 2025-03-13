create table reservations_history_item (
    reservation_history_item_id serial not null,
    reservation_history_id int not null,
    book_id             int not null,
    title               varchar(100) not null,
    quantity            int default 0,
    primary key (reservation_history_item_id),
    constraint fk_reservations_history_item_books
        foreign key (book_id)
            references books (book_id),
    constraint fk_reservations_history_item_reservations_history
        foreign key (reservation_history_id)
            references reservations_history (reservation_history_id)
);