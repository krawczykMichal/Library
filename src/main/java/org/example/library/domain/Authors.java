package org.example.library.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "authorId")
@ToString(of = {"authorId", "name", "surname"})
public class Authors {

    Integer authorId;
    String name;
    String surname;
    Set<Books> books;
}
