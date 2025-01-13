create table loans_request
(
    loan_request_id serial    not null,
    request_date    timestamp not null,
    book_id         int       not null,
    user_id         int       not null,
    primary key (loan_request_id),
    constraint fk_library_loan_request_books
        foreign key (book_id)
            references books (book_id),
    constraint fk_library_loan_request_users
        foreign key (user_id)
            references users (user_id)
);