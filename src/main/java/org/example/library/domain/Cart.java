package org.example.library.domain;

import lombok.*;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "cartId")
@ToString(of = {"cartId", "user", "cartItem"})
public class Cart {

    Integer cartId;
    Users user;
    List<CartItem> cartItem;
}
