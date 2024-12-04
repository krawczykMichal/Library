create table loan_request_item
(
    loan_request_item_id serial       not null,
    book_id              int          null,
    title                varchar(100) null,
    quantity             int          null,
    primary key (loan_request_item_id),
    constraint fk_library_app_loan_request_item_books
        foreign key (book_id)
            references books (book_id)
);