package org.example.library.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.infrastructure.security.business.validation.PasswordMatch;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch
public class PasswordDTO {

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż])(?=.*\\d)[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż\\d]{4,}$",
            message = "Password must be at least 4 characters, including 1 letter and 1 number")
    private String usersUserPassword;

    @NotEmpty(message = "Confirm Password cannot be empty")
    private String confirmPassword;
}
