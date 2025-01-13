create table reservations
(
    reservation_id   serial not null,
    reservation_date timestamp,
    primary key (reservation_id),
);