package org.example.library.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "reservationHistoryItemId")
@ToString(of = {"reservationHistoryItemId", "title", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations_history_item")
public class ReservationsHistoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_history_item_id")
    private Integer reservationHistoryItemId;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservationHistoryId")
    private ReservationsHistoryEntity reservationHistory;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BooksEntity book;
}
