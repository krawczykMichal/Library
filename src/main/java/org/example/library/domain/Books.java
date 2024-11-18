package org.example.library.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "bookId")
@ToString(of = {"bookId", "title", "isbn", "publisher", "publishedDate", "copies", "available"})
public class Books {

    Integer bookId;
    String title;
    String isbn;
    String publisher;
    Date publishedDate;
    Integer copies;
    Boolean available;
    Authors author;
    Categories category;
    CartItem cartItem;
    List<Loans> loans;
    List<Reservations> reservations;
}
