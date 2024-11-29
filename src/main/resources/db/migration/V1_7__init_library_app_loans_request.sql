create table loan_request
(
    loan_request_id serial    not null,
    request_date    timestamp not null,
    reservations_id int       not null,
    primary key (loan_request_id),
    constraint fk_library_loan_request_reservations
        foreign key (reservations_id)
            references reservations (reservations_id)
);