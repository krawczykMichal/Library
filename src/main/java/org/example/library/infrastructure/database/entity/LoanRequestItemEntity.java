package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "loanRequestItemId")
@ToString(of = {"loanRequestItemId", "title", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_request_item")
public class LoanRequestItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_request_item_id")
    private Integer loanRequestItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BooksEntity book;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;
}
