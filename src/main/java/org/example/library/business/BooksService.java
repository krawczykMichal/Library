package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.BooksDTO;
import org.example.library.business.dao.BooksDao;
import org.example.library.domain.Authors;
import org.example.library.domain.Books;
import org.example.library.domain.CartItem;
import org.example.library.domain.Categories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BooksService {

    private final CategoriesService categoriesService;
    private final AuthorsService authorsService;

    private final BooksDao booksDao;

    public List<Books> findAll() {
        return booksDao.findAll();
    }

    @Transactional
    public void saveBook(BooksDTO booksDTO) {
        Books book = registerBook(booksDTO);
        booksDao.saveBook(book);
    }

    private Books registerBook(BooksDTO booksDTO) {
        Authors author = authorsService.findByAuthorCode(booksDTO.getBooksAuthorCode());
        Categories category = categoriesService.findByName(booksDTO.getBooksCategoriesName());

        Books books = Books.builder()
                .title(booksDTO.getTitle())
                .isbn(booksDTO.getIsbn())
                .publisher(booksDTO.getPublisher())
                .publishedDate(booksDTO.getPublishedDate())
                .copies(booksDTO.getCopies())
                .available(true).build();
        return books.withAuthor(author).withCategory(category);
    }

    @Transactional
    public Books findByIsbn(String isbn) {
        if (booksDao.findByIsbn(isbn).isEmpty()) {
            throw new RuntimeException();
        }
        //@TODO poprawić to tak aby zamiast wyjątków i wyrzucania z aplikacji pojawiały się informacje na stronie która dalej działa np; nie możemy znaleźć książki o takich danych, spróbuj ponownie później
        return booksDao.findByIsbn(isbn).get();
    }

    @Transactional
    public void updateBook(String isbn, BooksDTO booksDTO) {
        Books byIsbn = findByIsbn(isbn);

        Books updatedBook = byIsbn.withTitle(booksDTO.getTitle())
                .withPublisher(booksDTO.getPublisher())
                .withIsbn(booksDTO.getIsbn())
                .withCopies(booksDTO.getCopies());

        booksDao.saveBook(updatedBook);
    }

    public List<Books> findByTitleInclude(String title) {
        return booksDao.findByTitleInclude(title);
    }

    public void changeCopies(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getCopies() < 1) {
                Books books = cartItem.getBook().withCopies(cartItem.getBook().getCopies() - cartItem.getQuantity()).withAvailable(false);
                booksDao.saveBook(books);
            } else {
                Books books = cartItem.getBook().withCopies(cartItem.getBook().getCopies() - cartItem.getQuantity());
                booksDao.saveBook(books);
            }
        }
    }
}
