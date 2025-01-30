package org.example.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksDTO {

    private Integer bookId;
    private String title;
    private String isbn;
    private String publisher;
    private Date publishedDate;
    private Integer copies;
    private Boolean available;

    private String booksAuthorName;
    private String booksAuthorSurname;
    private String booksAuthorCode;

    private String booksCategoriesName;



}
