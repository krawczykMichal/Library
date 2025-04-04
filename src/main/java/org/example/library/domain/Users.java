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
    String username;
    List<Loans> loans;
    List<Reservations> reservations;
    Cart cart;
    User user;
    Role role;
}
