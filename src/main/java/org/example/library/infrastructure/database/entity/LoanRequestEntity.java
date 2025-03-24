package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.library.domain.LoanRequestItem;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "loanRequestId")
@ToString(of = {"loanRequestId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans_request")
public class LoanRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_request_id")
    private Integer loanRequestId;

    @Column(name = "loan_request_number")
    private String loanRequestNumber;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanRequest")
    private List<LoanRequestItemEntity> loanRequestItems;

}
