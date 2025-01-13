package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "cartItemId")
@ToString(of = {"cartItemId", "book", "quantity"})
public class CartItem {

    Integer cartItemId;
    String title;
    Integer quantity;
    Books book;
    Cart cart;
}
