package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "loanRequestId")
@ToString(of = {"loanRequestId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_request")
public class LoanRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_request_id")
    private Integer loanRequestId;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservations_id")
    private ReservationsEntity reservation;

}
