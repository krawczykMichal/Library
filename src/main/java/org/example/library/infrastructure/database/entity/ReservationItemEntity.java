package org.example.library.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "reservationItemId")
@ToString(of = {"reservationItemId", "title", "quantity", "cart", "book"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation_item")
public class ReservationItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_item_id")
    private Integer reservationItemId;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private ReservationsEntity reservation;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BooksEntity book;
}
