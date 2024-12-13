create table loan_request
(
    loan_request_id serial    not null,
    request_date    timestamp not null,
    books_id        int       not null,
    users_id        int       not null,
    primary key (loan_request_id),
    constraint fk_library_loan_request_books
        foreign key (books_id)
            references books (books_id),
    constraint fk_library_loan_request_users
        foreign key (users_id)
            references users (users_id)
);