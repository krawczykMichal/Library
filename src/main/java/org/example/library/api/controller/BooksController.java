package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.library.api.dto.BooksDTO;
import org.example.library.business.*;
import org.example.library.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BooksController {

    private final BooksService booksService;
    private final CartService cartService;
    private final CategoriesService categoriesService;
    private final AuthorsService authorsService;
    private final UsersService usersService;
    private final CartItemService cartItemService;


    @GetMapping(value = "/employee/book/home")
    public String home(
    ) {
        return "employee_book_home";
    }

    @GetMapping(value = "/employee/book/add")
    public String addBookPage(
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO,
            BindingResult result,
            Model model
    ) {

        List<Categories> categories = categoriesService.findAll();
        List<Authors> authors = authorsService.findAll();
        if (result.hasErrors()) {
            model.addAttribute("booksDTO", booksDTO);  // Przekazujemy dane do widoku
            model.addAttribute("categories", categories);
            model.addAttribute("authors", authors);
        }

        model.addAttribute("booksDTO", booksDTO);
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        return "employee_book_add";
    }

    @PostMapping(value = "/employee/book/add")
    public String addBook(
            @ModelAttribute("booksDTO")
            @Valid BooksDTO booksDTO,
            BindingResult result,
            Model model,
            HttpSession httpSession
    ) {

        List<Categories> categories = categoriesService.findAll();
        List<Authors> authors = authorsService.findAll();

        if (result.hasErrors()) {
            System.out.println("errors: " + result.getAllErrors());
            model.addAttribute("booksDTO", booksDTO);
            model.addAttribute("categories", categories);
            model.addAttribute("authors", authors);
            return "employee_book_add";
        }
        booksService.saveBook(booksDTO);

        httpSession.setAttribute("book", booksDTO);

        model.addAttribute("booksDTO", booksDTO);
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);

        return "redirect:/employee/book/home";
    }

    @GetMapping(value = "/book/list")
    public String bookList(
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        List<Books> booksList = booksService.findAll();

        Users user = usersService.findByUsername(username);
        model.addAttribute("booksList", booksList);
        model.addAttribute("user", user);
        return "book_list";
    }

    @GetMapping(value = "/book/list/by/title/{title}")
    public String bookListByTitle(
            @PathVariable("title")
            String title,
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO,
            Model model
    ) {
        List<Books> booksListByTitle = booksService.findByTitleInclude(title);

        model.addAttribute("booksListByTitle", booksListByTitle);

        return "book_list_by_title";
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


}
