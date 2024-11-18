create table cart
(
    cart_id      serial not null,
    user_id      int    not null unique,
    cart_item_id int    not null,
    primary key (cart_id),
    constraint fk_cart_cart_item
        foreign key (cart_item_id)
            references cart_item (cart_item_id),
    constraint fk_cart_user
        foreign key (user_id)
            references users (user_id)
)