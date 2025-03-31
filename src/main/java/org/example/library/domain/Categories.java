package org.example.library.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "categoryId")
@ToString(of = {"categoryId", "name"})
public class Categories {

    Integer categoryId;
    @NotEmpty(message = "Category name cannot be empty")
    String name;
    List<Books> books;
}
