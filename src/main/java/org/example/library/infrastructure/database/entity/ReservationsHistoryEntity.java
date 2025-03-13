package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "reservationHistoryId")
@ToString(of = {"reservationHistoryId", "reservationMakeDate", "reservationHoldToDate"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations_history")
public class ReservationsHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_history_id")
    private Integer reservationHistoryId;

    @Column(name = "reservation_number")
    private String reservationNumber;

    @Column(name = "reservation_make_date")
    private LocalDateTime reservationMakeDate;

    @Column(name = "reservatin_hold_to_date")
    private LocalDateTime reservationHoldToDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservationHistory")
    private List<ReservationsHistoryItemEntity> reservationHistoryItems;
}
