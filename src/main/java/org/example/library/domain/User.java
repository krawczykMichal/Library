package org.example.library.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "userId")
@ToString(exclude = {"role"})
public class User {

    Integer userId;
    String username;
    String email;
    String password;
    Boolean active;
    Set<Role> role;

}
