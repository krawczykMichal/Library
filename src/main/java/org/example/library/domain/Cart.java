package org.example.library.domain;

import lombok.*;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "cartId")
@ToString(of = {"cartId", "userId", "cartItem"})
public class Cart {

    Integer cartId;
    Integer userId;
    List<CartItem> cartItem;
}
