package org.example.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Integer cartId;
    private String userUsername;
    private String cartItemBookTitle;
    private String cartItemBookAuthor;
    private String cartItemBookIsbn;
    private Integer cartItemQuantity;
}
