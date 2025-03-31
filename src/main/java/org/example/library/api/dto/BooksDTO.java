package org.example.library.api.dto;

import jakarta.validation.constraints.*;
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

    @NotEmpty(message = "Title cannot be empty")
    @Pattern(
            regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż0-9._]+$",
            message = "Title can only contain letters, numbers, and special characters (, . / ? \\ - _ = + ' \" ; : ` ! ~)"
    )
    private String title;

    @NotEmpty(message = "ISBN cannot be empty")
    @Pattern(
            regexp = "^(\\d{10}|\\d{13})$",
            message = "ISBN must be either 10 or 13 digits long"
    )
    private String isbn;

    @NotEmpty(message = "Publisher cannot be empty")
    @Pattern(
            regexp = "^[\\p{L}\\-' ]+$",
            message = "Publisher can only contain letters and the characters ( - , ')"
    )
    private String publisher;

    @NotNull(message = "Published date cannot be empty")
    @Min(value = 100, message = "Published year must be at least 3 digits long")
    @Max(value = 9999, message = "Published year cannot exceed 4 digits")
    private Integer publishedDate;

    private Integer copies;
    private Boolean available;

    private String booksAuthorCode;

    @NotEmpty(message = "Category name cannot be empty")
    @Pattern(
            regexp = "^[a-zA-ZĄąĆćĘęŁłŃńÓóŚśŹźŻż ]+$",
            message = "Category name can only contain Polish letters"
    )
    private String booksCategoriesName;

}
