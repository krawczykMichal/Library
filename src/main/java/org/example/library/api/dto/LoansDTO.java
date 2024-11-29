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
public class LoansDTO {

    private Integer loanId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private Boolean returned;

    private Integer loanRequestId;
}
