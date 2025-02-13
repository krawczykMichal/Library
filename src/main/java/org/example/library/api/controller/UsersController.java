package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.CartDTO;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.*;
import org.example.library.domain.*;
import org.example.library.infrastructure.security.business.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        //@TODO sprawdzić czy ta metoda działa

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

    @GetMapping(value = "/user/reservation/history/details/{reservationNumber}")
    public String userReservationHistoryDetails(
            @PathVariable String reservationNumber,
            Model model
    ) {
        Reservations reservation = reservationsService.findByReservationNumber(reservationNumber);
        System.out.println("reservation: " + reservation);
        List<ReservationItem> reservationItemList = reservation.getReservationItem();
        System.out.println("reservationItemList: " + reservationItemList);

        model.addAttribute("reservation", reservation);

        return "reservation_details";
    }

    @DeleteMapping(value = "/user/reservation/history/details/{reservationId}/delete")
    public String userReservationHistoryDelete(
            @PathVariable("reservationId")
            Integer reservationId
    ) {
        reservationsService.deleteById(reservationId);

        return "redirect:/user/reservation/history/{userId}";
    }

    @GetMapping(value = "/user/loan/history/{userId}")
    public String userLoanHistory(
            @PathVariable Integer userId,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId1 = userByUsername.getUserId();

        userId1 = userId;

        List<Loans> allLoans = loansService.findAllByUserId(userId);

        model.addAttribute("allLoans", allLoans);

        return "user_loan_list";
    }

    @GetMapping(value = "/user/loan/history/{loanNumber}/details")
    public String userLoanHistoryDetails(
            @PathVariable("loanNumber") String loanNumber,
            Model model,
            HttpSession httpSession
    ) {

        httpSession.getAttribute("username");
        Users user = usersService.findByUsername(httpSession.getAttribute("username").toString());
        Integer userId = user.getUserId();

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

//@TODO kontunuować dodawanie funkcjonlności dla użytkownika i pracownika dotycząćych historii rezerwacji, dostępu do danych użytkownika, patrzenia czy nie spóźnia się z oddaniem, itd

    private Integer getUserId(HttpSession httpSession) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        return userByUsername.getUserId();
    }
}
