package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "loanId")
@ToString(of = {"loanId", "loanDate", "returnDate", "returned"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class LoansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "returned")
    private Boolean returned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeesEntity employee;

    @OneToOne(mappedBy = "loanRequest")
    private LoanRequestEntity loanRequest;
}
