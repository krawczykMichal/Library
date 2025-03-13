package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "loansHistoryItemId")
@ToString(of = {"loansHistoryItemId", "title", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans_hsitory_item")
public class LoansHistoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loans_history_item_id")
    private Integer loansHistoryItemId;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loans_history_id")
    private LoansHistoryEntity loansHistory;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BooksEntity book;
}
