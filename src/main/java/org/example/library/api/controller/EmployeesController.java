package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.*;
import org.example.library.business.*;
import org.example.library.domain.*;
import org.example.library.domain.exception.NotFoundException;
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
    private final LoanRequestItemService loanRequestItemService;
    private final LoanItemService loanItemService;


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

        return "employee_home";
    }

    private String getEmailFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();
    }

    @GetMapping(value = "/employee/users/list")
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

    @GetMapping(value = "/employee/reservations/list")
    public String reservationsList(
            Model model
    ) {
        List<Reservations> reservationsList = reservationsService.findALl();

        model.addAttribute("reservationsList", reservationsList);

        return "employee_reservations_list";
    }

    @GetMapping(value = "/employee/reservation/details/{reservationNumber}")
    public String reservationDetailsForEmployee(
            @PathVariable("reservationNumber")
            String reservationNumber,
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model
    ) {

        Reservations reservation = reservationsService.findByReservationNumber(reservationNumber);

        model.addAttribute("reservation", reservation);
        model.addAttribute("reservationsDTO", reservationsDTO);

        return "employee_reservation_details";
    }

    @DeleteMapping(value = "/employee/reservation/details/{reservationNumber}/delete")
    public String userReservationHistoryDelete(
            @PathVariable("reservationNumber")
            String reservationNumber
    ) {
        reservationsService.cancelReservation(reservationNumber);

        return "redirect:/employee/reservations/list";
    }


    @GetMapping(value = "/employee/loan/request/list")
    public String loanPage(
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            Model model
    ) {
        List<LoanRequest> loanRequestList = loanRequestService.findAll();

        model.addAttribute("loanRequestDTO", loanRequestDTO);
        model.addAttribute("loanRequestList", loanRequestList);

        return "employee_loan_request_list";
    }

    @GetMapping(value = "/employee/loanRequest/details/{loanRequestNumber}")
    public String loanRequestDetails(
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            @PathVariable("loanRequestNumber")
            String loanRequestNumber,
            Model model
    ) {
        LoanRequest loanRequest = loanRequestService.findByLoanRequestNumber(loanRequestNumber);
        List<LoanRequestItem> loanRequestItem = loanRequestItemService.findByLoanRequestNumber(loanRequestNumber);
        System.out.println("loanRequestItem: " + loanRequestItem);

        model.addAttribute("loanRequestDTO", loanRequestDTO);
        model.addAttribute("loanRequest", loanRequest);
        model.addAttribute("loanRequestItem", loanRequestItem);

        return "employee_loan_request_details";
    }

    @PostMapping(value = "/employee/loanRequest/details/{loanRequestNumber}")
    public String loan(
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            @PathVariable("loanRequestNumber")
            String loanRequestNumber,
            Model model
    ) {

        LoanRequest loanRequest = loanRequestService.findByLoanRequestNumber(loanRequestNumber);
        List<LoanRequestItem> loanRequestItemList = loanRequest.getLoanRequestItems();


        loansService.makeLoan(loanRequest, loanRequestItemList);
        loanRequestService.deleteLoanRequest(loanRequest);

        model.addAttribute("loansDTO", loansDTO);

        return "redirect:/employee/loan/request/list";
    }

    @GetMapping(value = "/employee/loan/list")
    public String loanList(
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            Model model
    ) {

        List<Loans> loansList = loansService.findAllForEmployee();

        model.addAttribute("loansDTO", loansDTO);
        model.addAttribute("loansList", loansList);

        return "employee_loan_list";
    }

    @GetMapping(value = "/employee/loan/details/{loanNumber}")
    public String loanDetailsForEmployee(
            @PathVariable("loanNumber")
            String loanNumber,
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            Model model
    ) {

        Loans loan = loansService.findByLoanNumber(loanNumber);
        System.out.println("loan: " + loan);
        List<LoanItem> loanItems = loanItemService.findByLoanNumber(loanNumber);
        System.out.println("loanItem: " + loanItems);

        model.addAttribute("loan", loan);
        model.addAttribute("loanItems", loanItems);
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
        try {
            Books book = booksService.findByIsbn(isbn);
            model.addAttribute("book", book);
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", "Could not find book with: " + isbn + ". Try again or check your ISBN.");
            return "book_not_found_by_isbn";
        }

        return "employee_book_details";
    }

    @GetMapping(value = "/employee/book/{isbn}/details/update")
    public String bookDetailsUpdate(
            @PathVariable("isbn")
            String isbn,
            Model model,
            @ModelAttribute("booksDTO")
            BooksDTO booksDTO
    ) {
        Books book = booksService.findByIsbn(isbn);

        model.addAttribute("booksDTO", booksDTO);
// @TODO sprawdzić czy książka istnieje w bazie danych zanim ją zaktualizujemy
        return "employee_book_details_update";
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
