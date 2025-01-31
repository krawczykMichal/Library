create table loans_request
(
    loan_request_id serial    not null,
    request_date    timestamp not null,
    reservation_id         int       not null,
    cart_id         int       not null,
    primary key (loan_request_id),
    constraint fk_library_loan_request_reservations
        foreign key (reservation_id)
            references reservations (reservation_id),
    constraint fk_library_loan_request_cart
        foreign key (cart_id)
            references cart (cart_id)
);