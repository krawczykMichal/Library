create table loan_item
(
    loan_item_id serial not null,
    loan_id      int    not null,
    book_id      int null,
    title        varchar(100) null,
    quantity     int default 0,
    primary key (loan_item_id),
    constraint fk_loan_item_loan
        foreign key (loan_id)
            references loans (loan_id),
    constraint fk_loan_item_books
        foreign key (book_id)
            references books (book_id)
)