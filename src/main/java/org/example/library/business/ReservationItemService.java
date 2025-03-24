package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.BooksDao;
import org.example.library.business.dao.ReservationItemDao;
import org.example.library.business.dao.ReservationsDao;
import org.example.library.domain.Books;
import org.example.library.domain.CartItem;
import org.example.library.domain.ReservationItem;
import org.example.library.domain.Reservations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationItemService {
    private final ReservationItemDao reservationItemDao;
    private final BooksDao booksDao;
    private final ReservationsDao reservationsDao;

    @Transactional
    public void saveReservationItem(Reservations reservation, List<CartItem> cartItems) {
        Optional<Reservations> byReservationNumber = reservationsDao.findByReservationNumber(reservation.getReservationNumber());
        Reservations reservations = byReservationNumber.get();
        System.out.println("reservations: " + reservations);

        for (CartItem cartItem : cartItems) {
            ReservationItem reservationItem = ReservationItem.builder()
                    .title(cartItem.getTitle())
                    .book(cartItem.getBook())
                    .quantity(cartItem.getQuantity())
                    .reservation(reservations) // id na pewno nie będzie nullem
                    .build();


            updateBookCopies(List.of(reservationItem));

            reservationItemDao.save(reservationItem);
            System.out.println("reservationItem: " + reservationItem);
        }
    }

    private void updateBookCopies(List<ReservationItem> reservationItems) {
        for (ReservationItem item : reservationItems) {
            Books book = item.getBook();
            Books updatedBook = book.withCopies(book.getCopies() - item.getQuantity());
            booksDao.saveBook(updatedBook);
        }
    }

}
