package org.example.library.api.dto;

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
    private String name;
    private String surname;
    private String username;
    private String email;
    private String employeeNumber;
}
