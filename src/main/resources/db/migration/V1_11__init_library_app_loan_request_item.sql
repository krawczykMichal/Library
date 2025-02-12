create table loan_request_item
(
    loan_request_item_id serial not null,
    loan_request_id      int    not null,
    book_id              int null,
    title                varchar(100) null,
    quantity             int default 0,
    primary key (loan_request_item_id),
    constraint fk_loan_request_item_loan_request
        foreign key (loan_request_id)
            references loans_request (loan_request_id),
    constraint fk_loan_request_item_books
        foreign key (book_id)
            references books (book_id)
)