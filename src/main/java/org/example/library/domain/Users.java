package org.example.library.domain;

import lombok.*;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "name", "surname", "email", "phoneNumber"})
public class Users {

    Integer userId;
    String name;
    String surname;
    String email;
    String phoneNumber;
    List<Loans> loans;
    List<Reservations> reservations;
    User user;
    Role role;
}
