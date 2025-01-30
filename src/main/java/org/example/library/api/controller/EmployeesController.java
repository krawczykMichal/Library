package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.BooksDTO;
import org.example.library.api.dto.EmployeesDTO;
import org.example.library.api.dto.LoanRequestDTO;
import org.example.library.api.dto.LoansDTO;
import org.example.library.business.BooksService;
import org.example.library.business.EmployeesService;
import org.example.library.business.LoanRequestService;
import org.example.library.business.LoansService;
import org.example.library.domain.Books;
import org.example.library.domain.Employees;
import org.example.library.domain.LoanRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;
    private final LoansService loansService;
    private final LoanRequestService loanRequestService;
    private final BooksService booksService;


    @GetMapping(value = "/employee/home")
    public String home(
            Authentication authentication,
            Model model,
            HttpSession httpSession
    ) {
        authentication.isAuthenticated();
        log.debug("log authentication is: " + authentication);
        Object prin = authentication.getPrincipal();
        log.debug("log principal is instance of: " + prin.getClass());

        String username = getEmailFromAuthentication(authentication);

        httpSession.setAttribute("usernameEmployee", username);

        return "library_employee_home";
    }

    private String getEmailFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();
    }

    @GetMapping(value = "/employee/cart/loanRequest-list")
    public String loanPage(
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            Model model
    ) {
        loanRequestService.findAll();

        model.addAttribute("loanRequestDTO", loanRequestDTO);

        return "cart_loan_list";
    }

    @GetMapping(value = "/employee/cart/loanRequest/details/{loanRequestId}")
    public String loanRequestDetails(
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            @PathVariable("loanRequestId")
            Integer loanRequestId,
            Model model
    ) {
        loanRequestService.findById(loanRequestId);

        model.addAttribute("loanRequestDTO", loanRequestDTO);

        return "cart_loan_request_details";
    }

    @PostMapping(value = "/employee/cart/loanRequest/details/{loanRequestId}")
    public String loan(
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            @PathVariable("loanRequestId")
            Integer loanRequestId,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("usernameEmployee").toString();

        Employees employee = employeesService.findByUsername(username);
        LoanRequest loanRequest = loanRequestService.findById(loanRequestId);


        loansService.makeLoan(loanRequest, employee);

        model.addAttribute("loansDTO", loansDTO);

        return "redirect:/employee/cart/loanRequest-list";
    }

    @GetMapping(value = "/employee/book/list")
    public String bookPage(
            Model model
    ) {
        List<Books> booksList = booksService.findAll();

        model.addAttribute("booksList", booksList);
        return "employee_book_list";
    }

    @GetMapping(value = "/employee/book/{isbn}/details")
    public String bookDetails(
            @PathVariable("isbn")
            String isbn,
            Model model,
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO
    ) {
        booksService.findByIsbn(isbn);

        model.addAttribute("booksDTO", booksDTO);

        return "employee_book_details";
    }

    @PatchMapping(value = "/employee/book/{isbn}/details/update")
    public String update(
            @PathVariable("isbn")
            String isbn,
            Model model,
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO
    ) {

        booksService.updateBook(isbn, booksDTO);

        model.addAttribute("booksDTO", booksDTO);

        return "redirect:/employee/home";
    }

}
