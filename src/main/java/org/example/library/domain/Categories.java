package org.example.library.domain;

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
    String name;
    List<Books> books;
}
