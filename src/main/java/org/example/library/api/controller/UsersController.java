package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.CartDTO;
import org.example.library.api.dto.LoanRequestDTO;
import org.example.library.api.dto.PasswordDTO;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.*;
import org.example.library.domain.*;
import org.example.library.domain.exception.UserNameAlreadyTakenException;
import org.example.library.domain.exception.ValidationException;
import org.example.library.infrastructure.security.business.UserService;
import org.example.library.infrastructure.security.business.validation.PasswordValidationGroup;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

        String username = (String) httpSession.getAttribute("username");

        if (username == null) {
            // Jeśli username nie ma w sesji, pobieramy go z authentication
            username = getUsernameFromAuthentication(authentication);
            // Zapisujemy username w sesji, aby móc go używać później
            httpSession.setAttribute("username", username);
        }


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
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("usersDTO", usersDTO);  // Przekazujemy dane do widoku
        }

        if (model.containsAttribute("errorUsername")) {
            String errorMessage = (String) model.getAttribute("errorUsername");
            model.addAttribute("errorUsername", errorMessage);
        }

        return "users_register";
    }

    @PostMapping(value = "/user/register")
    public String userRegister(
            @ModelAttribute("usersDTO")
            @Valid UsersDTO usersDTO,
            BindingResult result,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        if (usersDTO.getPasswordDTO() != null) {
            usersDTO.getPasswordDTO().setConfirmPassword(usersDTO.getPasswordDTO().getUsersUserPassword());
        }

        if (result.hasErrors()) {
            System.out.println("errors: " + result.getAllErrors());
            model.addAttribute("usersDTO", usersDTO);
            return "users_register";
        }

        User user2 = userService.findByUsername(usersDTO.getUsername());
        if (user2 != null) {
            redirectAttributes.addFlashAttribute("errorUsername", "Username is already taken!");
            return "redirect:/user/register";
        }
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

    @GetMapping(value = "/user/{userId}/details/pass/change")
    public String changePasswordPage(
            @PathVariable("userId")
            Integer userId,
            @ModelAttribute("passwordDTO")
            PasswordDTO passwordDTO,
            HttpSession httpSession,
            Model model
    ) {
        Integer userId1 = getUserId(httpSession);

        userId1 = userId;

        Users user = usersService.findById(userId);
        System.out.println("user: " + user);

        model.addAttribute("passwordDTO", passwordDTO);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);

        return "user_change_password";
    }

    @PatchMapping(value = "/user/{userId}/details/pass/change")
    public String changePassword(
            @PathVariable("userId")
            Integer userId,
            @ModelAttribute("passwordDTO") @Validated PasswordDTO passwordDTO,
            BindingResult result,
            Model model,
            HttpSession httpSession
    ) {
        Integer userId1 = getUserId(httpSession);
        userId1 = userId;
        System.out.println("userId: " + userId);

        Users byId = usersService.findById(userId);


        // Sprawdzenie zgodności haseł
        if (!passwordDTO.getUsersUserPassword().equals(passwordDTO.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
        }

        // Jeśli wynik walidacji zawiera błędy, wróć do formularza
        if (result.hasErrors()) {
            System.out.println("errors: " + result.getAllErrors());
            return "user_change_password";
        }


        usersService.changePassword(userId, passwordDTO);

        model.addAttribute("passwordDTO", passwordDTO);
        model.addAttribute("user", byId);
        model.addAttribute("userId", userId);


        return "redirect:/user/{userId}/details";
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

        if (model.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", model.getAttribute("errorMessage"));
        }

        model.addAttribute("user", userByUsername);

        return "users_update";
    }

    @PatchMapping(value = "/user/{userId}/update")
    public String profileUpdate(
            @PathVariable Integer userId,
            @ModelAttribute("userDTO")
            UsersDTO usersDTO,
            Model model,
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ) {

        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId1 = userByUsername.getUserId();

        userId1 = userId;


        try {
            usersService.updateUsers(userId, usersDTO);
            if (!usersDTO.getUsername().isEmpty()) {
                httpSession.setAttribute("username", usersDTO.getUsername());
            }
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        } catch (UserNameAlreadyTakenException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addAttribute("userId", userId);
            return "redirect:/user/{userId}/update";
        } catch (ValidationException e) { // Obsługa błędów walidacji
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addAttribute("userId", userId);
            return "redirect:/user/{userId}/update";
        }

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
//
//    @GetMapping(value = "/user/reservation/history/details/{reservationNumber}")
//    public String userReservationHistoryDetails(
//            @PathVariable String reservationNumber,
//            Model model
//    ) {
//
//        ReservationsHistory reservationsHistory = reservationsHistoryService.findByReservationNumber(reservationNumber);
//
//        List<ReservationsHistoryItem> reservationsHistoryItemList = reservationsHistory.getReservationHistoryItems();
//
//        model.addAttribute("reservationsHistory", reservationsHistory);
//        model.addAttribute("reservationsHistoryItemList", reservationsHistoryItemList);
//
//        return "users_reservation_history_details";
//    }
//
//    @GetMapping(value = "/user/reservation/history/list/{userId}")
//    public String userReservationHistoryList(
//            @PathVariable Integer userId,
//            @ModelAttribute("cartDTO")
//            CartDTO cartDTO,
//            Model model,
//            HttpSession httpSession
//    ) {
//        userId = getUserId(httpSession);
//
//        List<ReservationsHistory> reservationsHistoryList = reservationsHistoryService.findAllByUserId(userId);
//
//        model.addAttribute("reservationsHistoryList", reservationsHistoryList);
//
//        return "users_reservation_history_list";
//    }

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

        return "user_loan_details";
    }

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


    private Integer getUserId(HttpSession httpSession) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        return userByUsername.getUserId();
    }
}
