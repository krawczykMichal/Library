package org.example.library.api.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDTO {

    private Integer loanRequestId;
    private LocalDateTime requestDate;

    private String loanRequestUsersName;
    private String loanRequestUsersSurname;
    private String loanRequestUsersUsername;
    @Email
    private String loanRequestUsersEmail;

    private String loanRequestBooksTitle;
    private String loanRequestBookIsbn;
}
