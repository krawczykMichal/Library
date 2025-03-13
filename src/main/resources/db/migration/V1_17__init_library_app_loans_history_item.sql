create table loans_history_item (
    loan_history_item_id serial not null,
    loan_history_id int not null,
    book_id      int null,
    title        varchar(100) null,
    quantity     int default 0,
    primary key (loan_history_item_id),
    constraint fk_loans_history_item_loans_history
        foreign key (loan_history_id)
            references loans_history (loan_history_id),
    constraint fk_loans_history_item_books
        foreign key (book_id)
            references books (book_id)
);