create table loans_history (
    loan_history_id serial not null,
    loan_number     varchar(10) not null,
    employee_id     int         not null,
    user_id         int         not null,
    loan_date       timestamp   not null,
    return_date     timestamp,
    primary key (loan_history_id),
    constraint fk_library_loans_history_employees
        foreign key (employee_id)
            references employees (employee_id),
    constraint fk_library_app_loans_history_users
        foreign key (user_id)
            references users (user_id)
);