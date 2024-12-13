create table reservations
(
    reservation_id   serial not null,
    user_id          int    not null,
    book_id          int    not null,
    reservation_date timestamp,
    primary key (reservation_id),
    constraint fk_library_reservation_users
        foreign key (user_id)
            references users (user_id),
    constraint fk_library_reservation_book
        foreign key (book_id)
            references books (book_id)

-- @TODO poprawić czyli przemyśleć koncepcje o wyciągnięciu rzeczy z koszyka skoro tylko w koszyku można zrobić loan i reservation i poprawić wszystkie rzeczy z tym związane
);