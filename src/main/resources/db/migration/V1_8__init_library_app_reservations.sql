create table reservations
(
    reservation_id           serial not null,
    cart_id                  int    not null,
    reservation_make_date    timestamp,
    reservation_hold_to_date timestamp,
    primary key (reservation_id),
    constraint fk_reservations_cart
        foreign key (cart_id)
            references cart (cart_id)
);