package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.library.api.dto.*;
import org.example.library.business.*;
import org.example.library.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UsersService usersService;
    private final BooksService booksService;
    private final ReservationsService reservationsService;
    private final LoanRequestService loanRequestService;
    private final LoansService loansService;


    @GetMapping(value = "/cart/{userId}/create")
    public String createCartPage(
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

        model.addAttribute("cartDTO", cartDTO);

        return "cart_create";
    }

    @PostMapping(value = "/cart/{userId}/create")
    public String createCart(
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

        cartService.saveCart(cartDTO, userId);

        model.addAttribute("cartDTO", cartDTO);

        return "redirect:/user/home";
    }

    @GetMapping(value = "/cart/{userId}/details")
    public String cartDetailsPage(
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

        Cart cart = cartService.findCartByUserId(userId);

        List<CartItem> cartItem = cart.getCartItem();

        model.addAttribute("cartItem", cartItem);

        return "cart_details";
    }

    @GetMapping(value = "/cart/reservation")
    public String reservationPage(
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model
    ) {

        model.addAttribute("reservationsDTO", reservationsDTO);

        return "cart_reservation";
    }

    @PostMapping(value = "/cart/reservation")
    public String reservation(
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        String isbn = httpSession.getAttribute("isbn").toString();
        Books book = booksService.findByIsbn(isbn);

        reservationsService.makeReservation(userByUsername, book);

        model.addAttribute("reservationDTO", reservationsDTO);

        return "redirect:/user/home";
    }

    @GetMapping(value = "/cart/loan/request")
    public String loanRequestPage(
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model
    ) {

        model.addAttribute("reservationsDTO", reservationsDTO);

        return "cart_loan_request";
    }

    @PostMapping(value = "/cart/loan/request")
    public String loanRequest(
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);
        Integer userId = userByUsername.getUserId();

        Reservations reservation = reservationsService.findByUserId(userId);

        loanRequestService.loanRequest(reservation);

        model.addAttribute("reservationDTO", reservationsDTO);

        return "loan_request_success";
    }

    @GetMapping(value = "/employee/cart/loan")
    public String loanPage(
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            Model model
    ) {
        model.addAttribute("loansDTO", loansDTO);

        return "cart_loan";
    }

    @PostMapping(value = "/employee/cart/loan")
    public String loan(
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            Model model,
            HttpSession httpSession
    ) {
        loansService.makeLoan();
    }


}
