package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "loanItemId")
@ToString(of = {"loanItemId", "title", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_item")
public class LoanItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_item_id")
    private Integer loanItemId;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private LoansEntity loan;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BooksEntity book;
}
