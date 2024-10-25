package org.example.library.api.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private Integer userId;
    private String name;
    private String surname;
    @Email
    private String email;
    private String phoneNumber;
    private String username;

    @Email
    private String usersUserEmail;
    private String usersUserPassword;
    private Boolean usersUserActive;
    private String usersRole;

}













