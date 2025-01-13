create table cart
(
    cart_id      serial not null,
    user_id      int    not null unique,
    primary key (cart_id),
    constraint fk_cart_user
        foreign key (user_id)
            references users (user_id)
)