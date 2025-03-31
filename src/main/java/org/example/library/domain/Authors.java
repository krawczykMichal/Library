package org.example.library.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "authorId")
@ToString(of = {"authorId", "name", "surname"})
public class Authors {

    Integer authorId;

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(
            regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż]+$",
            message = "Name can only contain letters (including Polish characters)"
    )
    String name;

    @NotEmpty(message = "Surname cannot be empty")
    @Pattern(
            regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż]+$",
            message = "Surname can only contain letters (including Polish characters)"
    )
    String surname;
    String authorCode;
    List<Books> books;
}
