package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "returned")
    private Boolean returned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeesEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    private List<LoanItemEntity> loanItems;

    @OneToOne
    @JoinColumn(name = "loan_request_id", nullable = false, unique = true)
    private LoanRequestEntity loanRequest;
}
