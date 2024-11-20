package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.library.api.dto.BooksDTO;
import org.example.library.business.BooksService;
import org.example.library.business.CartItemService;
import org.example.library.business.CartService;
import org.example.library.domain.Books;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BooksController {

    private final BooksService booksService;
    private final CartItemService cartItemService;


    @GetMapping(value = "/book/home")
    public String home(
            Model model
    ) {
        return "book_home";
    }

    @GetMapping(value = "/book/list")
    public String bookList(
            Model model
    ) {
        List<Books> booksList = booksService.findAll();

        model.addAttribute("booksList", booksList);
        return "book_list";
    }

    @GetMapping(value = "/book/{isbn}/details")
    public String bookDetailsPage(
            @PathVariable("isbn")
            String isbn,
            Model model,
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO
    ) {
       Books book = booksService.findByIsbn(isbn);

       model.addAttribute("book", book);

        return "book_details";
    }
    @PostMapping(value = "/book/{isbn}/details")
    public String bookDetails(
            @PathVariable("isbn")
            String isbn,
            Model model,
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO
    ) {
       Books book = booksService.findByIsbn(isbn);

       cartItemService.addToCartItem(book);

       model.addAttribute("book", book);

        return "redirect:/book/list";
    }


    @GetMapping(value = "/book/add")
    public String addBookPage(
            @ModelAttribute("booksDTO")
            BooksDTO bookDTO,
            Model model
    ) {
        model.addAttribute("booksDTO", bookDTO);

        return "book_add";
    }

    @PostMapping(value = "/book/add")
    public String addBook(
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO,
            Model model,
            HttpSession httpSession
    ) {
        booksService.saveBook(booksDTO);

        httpSession.setAttribute("book", booksDTO);

        model.addAttribute("booksDTO", booksDTO);

        return "redirect:/book/home";
    }


}
