create table loans_request
(
    loan_request_id     serial      not null,
    loan_request_number varchar(10) not null unique,
    request_date        timestamp   not null,
    cart_id             int         null,
    reservation_id      int         null,
    user_id             int         not null,
    primary key (loan_request_id),
    constraint fk_library_loan_request_cart
        foreign key (cart_id)
            references cart (cart_id),
    constraint fk_library_loan_request_reservations
        foreign key (reservation_id)
            references reservations (reservation_id)
            ON DELETE SET NULL,
    constraint fk_library_app_loan_request_users
        foreign key (user_id)
            references users (user_id)
);