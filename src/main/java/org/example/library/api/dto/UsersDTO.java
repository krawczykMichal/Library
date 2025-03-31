package org.example.library.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.infrastructure.security.business.validation.PasswordMatch;
import org.example.library.infrastructure.security.business.validation.PasswordValidationGroup;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private Integer userId;
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(
            regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż]+$",
            message = "Name can only contain letters (including Polish characters)"
    )
    private String name;

    @NotEmpty(message = "Last name cannot be empty")
    @Pattern(
            regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż]+$",
            message = "Last name can only contain letters (including Polish characters)"
    )
    private String surname;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be valid (e.g., name@email.com")
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(
            regexp = "^\\+?[0-9 ]{7,15}$",
            message = "Phone number must be valid (e.g., +48 123 456 789)"
    )
    private String phoneNumber;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(
            regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż0-9._]+$",
            message = "Username can only contain letters, numbers, dots, and underscores"

    )
    private String username;

    @Email
    private String usersUserEmail;
    private Boolean usersUserActive;
    private String usersRole;

    @Valid
    private PasswordDTO passwordDTO;

}













