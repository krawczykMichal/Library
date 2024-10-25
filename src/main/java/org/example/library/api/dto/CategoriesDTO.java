package org.example.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesDTO {

    private Integer categoryId;
    private String name;

    private String categoryBooksTitle;
}
