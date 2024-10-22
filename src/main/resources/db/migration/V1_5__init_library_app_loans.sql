create table loans
(
    loan_id     serial    not null,
    user_id     int       not null,
    book_id     int       not null,
    loan_date   timestamp not null,
    return_date timestamp,
    returned    boolean default false,
    primary key (loan_id),
    constraint fk_library_loans_users
        foreign key (user_id)
            references users (user_id),
    constraint fk_library_loans_books
        foreign key (book_id)
            references books (book_id)
);