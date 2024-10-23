package org.example.library.domain;

import lombok.*;

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
    Role role;

}
