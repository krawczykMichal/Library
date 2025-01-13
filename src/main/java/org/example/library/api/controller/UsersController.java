package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.LoansDTO;
import org.example.library.api.dto.ReservationsDTO;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.CartService;
import org.example.library.business.LoansService;
import org.example.library.business.ReservationsService;
import org.example.library.business.UsersService;
import org.example.library.domain.Loans;
import org.example.library.domain.Users;
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
    private final ReservationsService reservationsService;


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
            Model model,
            HttpSession httpSession
    ) {
        usersService.saveUser(usersDTO);

        httpSession.setAttribute("userEmail", usersDTO.getEmail());

        return "redirect:/user/home";
    }

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

        return "users_home";
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();
    }

    @GetMapping(value = "/user/{userId}/details")
    public String userDetailsPage(
            @PathVariable Integer userId,
            @ModelAttribute("user")
            Users user,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId1 = userByUsername.getUserId();

        userId1 = userId;

        model.addAttribute("user", userByUsername);

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
            @PathVariable Integer userId
    ) {
        usersService.deleteById(userId);

        return "redirect:/";
    }

    @GetMapping(value = "/user/loan/history/{userId}")
    public String userLoanHistory(
            @PathVariable Integer userId,
            @RequestParam(value = "returned", defaultValue = "all") String returned,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId1 = userByUsername.getUserId();

        userId1 = userId;

        List<Loans> allLoans;

        if (returned.equals("false")) {
            allLoans = loansService.findAllByUserId(userId, false);
        } else if (returned.equals("true")) {
            allLoans = loansService.findAllByUserId(userId, true);
        } else {
            allLoans = loansService.findAllByUserId(userId);
        }

        model.addAttribute("allLoans", allLoans);

        return "user_loan_history";
    }

    @GetMapping(value = "/user/loan/history/{loanId}/details")
    public String userLoanHistoryDetails(
            @PathVariable Integer loanId,
            @ModelAttribute("loanDTO")
            LoansDTO loansDTO,
            Model model
    ) {
        loansService.findById(loanId);

        model.addAttribute("loansDTO", loansDTO);

        return "user_loan_history_details";
    }
//@TODO kontunuować dodawanie funkcjonlności dla użytkownika i pracownika dotycząćych historii rezerwacji, dostępu do danych użytkownika, patrzenia czy nie spóźnia się z oddaniem, itd

}
