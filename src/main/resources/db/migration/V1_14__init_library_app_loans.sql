create table loans
(
    loan_id         serial      not null,
    loan_number     varchar(10) not null,
    user_id         int         not null,
    loan_date       timestamp   not null,
    return_date     timestamp,
    returned        boolean default false,
    primary key (loan_id),
        foreign key (user_id)
            references users (user_id)
);