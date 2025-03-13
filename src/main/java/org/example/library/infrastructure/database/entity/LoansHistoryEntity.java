package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "loanHistoryId")
@ToString(of = {"loanHistoryId", "loanNumber", "loanDate", "returnDate"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans_history")
public class LoansHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_history_id")
    private Integer loanHistoryId;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeesEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loansHistory")
    private List<LoansHistoryItemEntity> loansHistoryItems;

}
