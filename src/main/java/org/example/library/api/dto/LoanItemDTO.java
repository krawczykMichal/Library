package org.example.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanItemDTO {

    private Integer cartItemId;
    private String bookTitle;
    private Integer quantity;
    private Boolean bookAvailable;
}
