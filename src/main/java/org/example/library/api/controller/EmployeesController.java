package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.*;
import org.example.library.business.*;
import org.example.library.domain.*;
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
    private final UsersService usersService;
    private final ReservationsService reservationsService;


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

    @GetMapping(value = "/employee/users-list")
    public String usersList(
            @ModelAttribute("usersDTO")
            UsersDTO usersDTO,
            Model model
    ) {
        List<Users> usersList = usersService.findAll();

        model.addAttribute("usersList", usersList);
        model.addAttribute("usersDTO", usersDTO);

        return "employee_users_list";
    }

    @GetMapping(value = "/employee/user/details/{userId}")
    public String userDetailsForEmployee(
            @PathVariable("userId")
            Integer userId,
            @ModelAttribute("usersDTO")
            UsersDTO usersDTO,
            Model model
    ) {
        Users user = usersService.findById(userId);
        List<Reservations> reservationsByUserId = reservationsService.findByUserId(userId);
        List<LoanRequest> loanRequestListByUserId = loanRequestService.findByUserId(userId);
        List<Loans> loansListByUserId = loansService.findAllByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("usersDTO", usersDTO);
        model.addAttribute("reservationsByUserId", reservationsByUserId);
        model.addAttribute("loanRequestListByUserId", loanRequestListByUserId);
        model.addAttribute("loansListByUserId", loansListByUserId);

        return "employee_user_details";
    }

    @GetMapping(value = "/employee/reservation/details/{reservationId}")
    public String reservationDetailsForEmployee(
            @PathVariable("reservationId")
            Integer reservationId,
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model
    ) {

        Reservations reservation = reservationsService.findById(reservationId);
        

        model.addAttribute("reservation", reservation);
        model.addAttribute("reservationsDTO", reservationsDTO);

        return "employee_reservation_details";
    }

    @GetMapping(value = "/employee/cart/loanRequest-list")
    public String loanPage(
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            Model model
    ) {
        List<LoanRequest> loanRequestList = loanRequestService.findAll();

        model.addAttribute("loanRequestDTO", loanRequestDTO);
        model.addAttribute("loanRequestList", loanRequestList);

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
        LoanRequest loanRequestById = loanRequestService.findById(loanRequestId);

        model.addAttribute("loanRequestDTO", loanRequestDTO);
        model.addAttribute("loanRequestById", loanRequestById);

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

    @GetMapping(value = "/employee/loan-list")
    public String loanList(
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            Model model
    ) {

        List<Loans> loansList = loansService.findAll();

        model.addAttribute("loansDTO", loansDTO);
        model.addAttribute("loansList", loansList);

        return "employee_loan_list";
    }

    @GetMapping(value = "/employee/loan/details/{loanId}")
    public String loanDetailsForEmployee(
            @PathVariable("loanId")
            Integer loanId,
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            Model model
    ) {

        Loans loan = loansService.findById(loanId);

        model.addAttribute("loan", loan);
        model.addAttribute("loansDTO", loansDTO);

        return "employee_loan_details";
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
