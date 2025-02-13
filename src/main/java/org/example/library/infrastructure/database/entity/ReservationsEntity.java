package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "reservationsId")
@ToString(of = {"reservationsId", "reservationMakeDate", "reservationHoldToDate"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class ReservationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer reservationsId;

    @Column(name = "reservation_number")
    private String reservationNumber;

    @Column(name = "reservation_make_date")
    private LocalDateTime reservationMakeDate;

    @Column(name = "reservation_hold_to_date")
    private LocalDateTime reservationHoldToDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<LoanRequestEntity> loanRequests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<ReservationItemEntity> reservationItems;
}
