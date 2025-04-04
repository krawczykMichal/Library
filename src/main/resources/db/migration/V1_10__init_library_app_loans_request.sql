create table loans_request
(
    loan_request_id     serial      not null,
    loan_request_number varchar(10) not null unique,
    request_date        timestamp   not null,
    user_id             int         not null,
    primary key (loan_request_id),
    constraint fk_library_app_loan_request_users
        foreign key (user_id)
            references users (user_id)
);