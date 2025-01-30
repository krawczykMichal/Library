package org.example.library.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "authorId")
@ToString(of = {"authorId", "name", "surname", "authorCode"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class AuthorsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "author_code")
    private String authorCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<BooksEntity> books;
}
