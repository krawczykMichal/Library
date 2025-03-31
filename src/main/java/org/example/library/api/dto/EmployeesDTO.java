package org.example.library.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesDTO {

    private Integer employeeId;

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

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(
            regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż0-9._]+$",
            message = "Username can only contain letters, numbers, dots, and underscores"
    )
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be valid (e.g., name@email.com")
    private String email;
    private String employeeNumber;
}
