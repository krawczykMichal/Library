create table reservations_history
(
    reservation_history_id   serial      not null,
    reservation_number       varchar(10) not null,
    reservation_make_date    timestamp,
    reservation_hold_to_date timestamp,
    user_id                  int         not null,
    primary key (reservation_history_id),
    constraint fk_library_app_reservations_history_users
        foreign key (user_id)
            references users (user_id)
);