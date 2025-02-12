package org.example.library.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "roleId", "userId"})
public class UserRole {

    Integer id;
    Integer roleId;
    Integer userId;
}
