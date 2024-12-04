create table loan_request
(
    loan_request_id      serial    not null,
    request_date         timestamp not null,
    loan_request_item_id int       not null,
    primary key (loan_request_id),
    constraint fk_library_loan_request_loan_request_item
        foreign key (loan_request_item_id)
            references loan_request_item (loan_request_item_id)
);