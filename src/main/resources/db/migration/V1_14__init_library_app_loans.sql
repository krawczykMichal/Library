create table loans
(
    loan_id         serial      not null,
    loan_number     varchar(10) not null,
    loan_request_id int         not null,
    employee_id     int         not null,
    user_id         int         not null,
    loan_date       timestamp   not null,
    return_date     timestamp,
    returned        boolean default false,
    primary key (loan_id),
    constraint fk_library_loans_loan_request
        foreign key (loan_request_id)
            references loans_request (loan_request_id),
    constraint fk_library_loans_employees
        foreign key (employee_id)
            references employees (employee_id),
    constraint fk_library_app_loans_users
        foreign key (user_id)
            references users (user_id)
);