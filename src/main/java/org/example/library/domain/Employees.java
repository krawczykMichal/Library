package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "employeeId")
@ToString(of = {"employeeId", "name", "surname"})
public class Employees {

    Integer employeeId;
    String name;
    String surname;
    String username;
    String email;
    String employeeNumber;
}
