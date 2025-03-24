package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.CartDTO;
import org.example.library.api.dto.LoanRequestDTO;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.*;
import org.example.library.domain.*;
import org.example.library.infrastructure.security.business.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final LoansService loansService;
    private final CartService cartService;
    private final ReservationsService reservationsService;
    private final CartItemService cartItemService;
    private final LoanItemService loanItemService;
    private final UserService userService;
    private final ReservationsHistoryService reservationsHistoryService;
    private final LoansHistoryService loansHistoryService;
    private final LoanRequestService loanRequestService;

    @GetMapping(value = "/user/home")
    public String userHome(
            Authentication authentication,
            @ModelAttribute("usersDTO")
            UsersDTO usersDTO,
            Model model,
            HttpSession httpSession
    ) {
        authentication.isAuthenticated();
        log.debug("log authentication is: " + authentication);
        Object prin = authentication.getPrincipal();
        log.debug("log principal is instance of: " + prin.getClass());

        String username = getUsernameFromAuthentication(authentication);

        Users userByUsername = usersService.findByUsername(username);

        httpSession.setAttribute("username", username);
        httpSession.setAttribute("user", userByUsername);
        model.addAttribute("usersDTO", usersDTO);
        model.addAttribute("user", userByUsername);

        return "users_home";
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();
    }

    @GetMapping(value = "/user/register")
    public String userRegisterPage(
            @ModelAttribute("usersDTO")
            UsersDTO usersDTO,
            Model model
    ) {
        model.addAttribute("usersDTO", usersDTO);

        return "users_register";
    }

    @PostMapping(value = "/user/register")
    public String userRegister(
            @ModelAttribute("usersDTO")
            UsersDTO usersDTO,
            HttpSession httpSession
    ) {
        Users user = usersService.saveUser(usersDTO);
        String username = user.getUsername();
        Users user1 = usersService.findByUsername(username);
        cartService.saveCart(user1);
        usersService.assignRoleToUser(username);

        httpSession.setAttribute("userEmail", usersDTO.getEmail());

        return "redirect:/user/home";
    }

    @GetMapping(value = "/user/{userId}/details")
    public String userDetailsPage(
            @ModelAttribute("user")
            Users user,
            Model model,
            HttpSession httpSession
    ) {

        Integer userId = getUserId(httpSession);

        Users userById = usersService.findById(userId);

        model.addAttribute("user", userById);

        return "users_details";
    }

    @GetMapping(value = "/user/{userId}/update")
    public String userUpdatePage(
            @PathVariable Integer userId,
            @ModelAttribute("userDTO")
            UsersDTO usersDTO,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId1 = userByUsername.getUserId();

        userId1 = userId;

        usersService.findById(userId);

        model.addAttribute("user", userByUsername);

        return "users_update";
    }

    @PatchMapping(value = "/user/{userId}/update")
    public String profileUpdate(
            @PathVariable Integer userId,
            @ModelAttribute("userDTO")
            UsersDTO usersDTO,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId1 = userByUsername.getUserId();

        userId1 = userId;

        usersService.updateUsers(userId, usersDTO);

        model.addAttribute("userDTO", usersDTO);

        return "redirect:/user/{userId}/details";
    }

    @DeleteMapping("/user/{userId}/delete")
    public String userDelete(
            @PathVariable("userId") Integer userId
    ) {
        System.out.println("userId: " + userId);
        Users byId = usersService.findById(userId);
        Integer id = byId.getUserId();
        usersService.deleteById(id);
        //@TODO zrobić tak że logowanie na nieistniejącego użytkownika nie rzuca błędu a prosi o ponowne wpisanie danych do logowania

        return "redirect:/";
    }


    @GetMapping(value = "/user/reservation/list/{userId}")
    public String userReservationList(
            @PathVariable Integer userId,
            @ModelAttribute("cartDTO")
            CartDTO cartDTO,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId1 = userByUsername.getUserId();

        userId1 = userId;

        List<Reservations> reservationsList = reservationsService.findByUserId(userId);

        model.addAttribute("cartDTO", cartDTO);
        model.addAttribute("reservationsList", reservationsList);

        return "users_reservations_list";
    }

    @GetMapping(value = "/user/reservation/details/{reservationNumber}")
    public String userReservationDetails(
            @PathVariable String reservationNumber,
            Model model
    ) {
        Reservations reservation = reservationsService.findByReservationNumber(reservationNumber);
        System.out.println("reservation: " + reservation);
        List<ReservationItem> reservationItemList = reservation.getReservationItem();
        System.out.println("reservationItemList: " + reservationItemList);

        model.addAttribute("reservationItemList", reservationItemList);
        model.addAttribute("reservation", reservation);

        return "reservation_details";
    }


    @DeleteMapping(value = "/user/reservation/details/{reservationNumber}/delete")
    public String userReservationDelete(
            @PathVariable("reservationNumber")
            String reservationNumber,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ) {
        Integer userId = getUserId(httpSession);
        reservationsService.cancelReservation(reservationNumber);
        redirectAttributes.addAttribute("userId", userId);

        return "redirect:/user/reservation/list/{userId}";
    }

    @PostMapping(value = "/user/loan/request/make/{reservationNumber}")
    public String makeRequest(
            @PathVariable("reservationNumber")
            String reservationNumber,
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            HttpSession httpSession
    ) {
        Integer userId = getUserId(httpSession);
        Users user = usersService.findById(userId);
        Reservations reservation = reservationsService.findByReservationNumber(reservationNumber);
        loanRequestService.makeLoanRequestFromReservation(reservation, user);
        reservationsService.deleteByReservationNumber(reservationNumber);


        return "redirect:/user/loan/request/list";
    }

    @GetMapping(value = "/user/loan/request/list")
    public String userLoanRequestList(
            HttpSession httpSession,
            Model model,
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO
    ) {
        Integer userId = getUserId(httpSession);
        List<LoanRequest> loanRequestList = loanRequestService.findByUserId(userId);
        model.addAttribute("loanRequestDTO", loanRequestDTO);
        model.addAttribute("loanRequestList", loanRequestList);

        return "user_loan_request_list";
    }

    @GetMapping(value = "/user/loan/request/{loanRequestNumber}/details")
    public String userLoanRequestDetails(
            @PathVariable("loanRequestNumber")
            String loanRequestNumber,
            Model model
    ) {
        LoanRequest byLoanRequestNumber = loanRequestService.findByLoanRequestNumber(loanRequestNumber);
        List<LoanRequestItem> loanRequestItems = byLoanRequestNumber.getLoanRequestItems();
        System.out.println("loanRequestItems: " + loanRequestItems);

        model.addAttribute("loanRequestItems", loanRequestItems);
        model.addAttribute("byLoanRequestNumber", byLoanRequestNumber);

        return "user_loan_request_details";
    }

    @DeleteMapping(value = "/user/loan/request/{loanRequestNumber}/delete")
    public String userLoanRequestDelete(
            @PathVariable("loanRequestNumber")
            String loanRequestNumber
    ) {
        LoanRequest byLoanRequestNumber = loanRequestService.findByLoanRequestNumber(loanRequestNumber);
        loanRequestService.deleteLoanRequest(byLoanRequestNumber);

        return "redirect:/user/loan/request/list";
    }

    @GetMapping(value = "/user/reservation/history/details/{reservationNumber}")
    public String userReservationHistoryDetails(
            @PathVariable String reservationNumber,
            Model model
    ) {

        ReservationsHistory reservationsHistory = reservationsHistoryService.findByReservationNumber(reservationNumber);

        List<ReservationsHistoryItem> reservationsHistoryItemList = reservationsHistory.getReservationHistoryItems();

        model.addAttribute("reservationsHistory", reservationsHistory);
        model.addAttribute("reservationsHistoryItemList", reservationsHistoryItemList);

        return "users_reservation_history_details";
    }

    @GetMapping(value = "/user/reservation/history/list/{userId}")
    public String userReservationHistoryList(
            @PathVariable Integer userId,
            @ModelAttribute("cartDTO")
            CartDTO cartDTO,
            Model model,
            HttpSession httpSession
    ) {
        userId = getUserId(httpSession);

        List<ReservationsHistory> reservationsHistoryList = reservationsHistoryService.findAllByUserId(userId);

        model.addAttribute("reservationsHistoryList", reservationsHistoryList);

        return "users_reservation_history_list";
    }

    @GetMapping(value = "/user/loan/list/{userId}")
    public String userLoanList(
            @PathVariable Integer userId,
            Model model,
            HttpSession httpSession
    ) {
        userId = getUserId(httpSession);

        List<Loans> allLoans = loansService.findAllByUserId(userId);

        model.addAttribute("allLoans", allLoans);

        return "user_loan_list";
    }

    @GetMapping(value = "/user/loan/{loanNumber}/details")
    public String userLoanDetails(
            @PathVariable("loanNumber") String loanNumber,
            Model model
    ) {

        Loans loan = loansService.findByLoanNumber(loanNumber);
        Integer loanId = loan.getLoanId();
        List<LoanItem> loanItemList = loanItemService.findAllByLoanId(loanId);

        System.out.println("loan: " + loan);
        System.out.println("loanItemList: " + loanItemList);
        model.addAttribute("loan", loan);
        model.addAttribute("loanItemList", loanItemList);
//        model.addAttribute("cartItem", cartItem);

        return "user_loan_details";
    }

//    @GetMapping(value = "/user/loan/{loanNumber}/details/returned")
//    public String userLoanDetailsAfterReturn(
//            @PathVariable("loanNumber") String loanNumber,
//            Model model
//    ) {
//
//        Loans loan = loansService.findByLoanNumber(loanNumber);
//        Integer loanId = loan.getLoanId();
//        List<LoanItem> loanItemList = loanItemService.findAllByLoanId(loanId);
//
//        System.out.println("loan: " + loan);
//        System.out.println("loanItemList: " + loanItemList);
//        model.addAttribute("loan", loan);
//        model.addAttribute("loanItemList", loanItemList);

    /// /        model.addAttribute("cartItem", cartItem);
//
//        return "user_loan_details_after_return";
//    }
    @PatchMapping(value = "/user/loan/{loanNumber}/return")
    public String userLoanReturn(
            @PathVariable("loanNumber") String loanNumber,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ) {
        Integer userId = getUserId(httpSession);
        loansService.returnLoan(loanNumber);
        System.out.println("loanNumber: " + loanNumber);

        redirectAttributes.addAttribute("userId", userId);

        return "redirect:/user/loan/list/{userId}";
    }

//@TODO kontunuować dodawanie funkcjonlności dla użytkownika i pracownika dotycząćych historii rezerwacji, dostępu do danych użytkownika, patrzenia czy nie spóźnia się z oddaniem, itd

    private Integer getUserId(HttpSession httpSession) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        return userByUsername.getUserId();
    }
}
