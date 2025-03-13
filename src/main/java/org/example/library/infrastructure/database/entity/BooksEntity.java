package org.example.library.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "bookId")
@ToString(of = {"bookId", "title", "isbn", "publisher", "publishedDate", "copies", "available"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BooksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "published_date")
    private Integer publishedDate;

    @Column(name = "copies")
    private Integer copies;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorsEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<CartItemEntity> cartItems;
}
