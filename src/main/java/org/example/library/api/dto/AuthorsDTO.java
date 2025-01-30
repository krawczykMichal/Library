package org.example.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorsDTO {

    private Integer authorId;
    private String name;
    private String surname;
    private String authorCode;
    private String authorBooksTitle;
    private Integer authorBooksCopies;
    private Boolean authorBooksAvailable;

}
