package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "loanRequestId")
@ToString(of = {"loanRequestId"})
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

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_request_id")
    private LoanRequestEntity loanRequest;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BooksEntity books;
}
