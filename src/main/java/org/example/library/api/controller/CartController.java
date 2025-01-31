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
    private final ReservationsService reservationsService;
    private final LoanRequestService loanRequestService;
    private final BooksService booksService;


//    @GetMapping(value = "/cart/{userId}/create")
//    public String createCartPage(
//            @PathVariable Integer userId,
//            @ModelAttribute("cartDTO")
//            CartDTO cartDTO,
//            Model model,
//            HttpSession httpSession
//    ) {
//
//        String username = httpSession.getAttribute("username").toString();
//        Users userByUsername = usersService.findByUsername(username);
//
//        Integer userId1 = userByUsername.getUserId();
//
//        userId1 = userId;
//
//        model.addAttribute("cartDTO", cartDTO);
//
//        return "cart_create";
//    }
//
//    @PostMapping(value = "/cart/{userId}/create")
//    public String createCart(
//            @PathVariable Integer userId,
//            @ModelAttribute("cartDTO")
//            CartDTO cartDTO,
//            Model model,
//            HttpSession httpSession
//    ) {
//        String username = httpSession.getAttribute("username").toString();
//        Users userByUsername = usersService.findByUsername(username);
//
//        Integer userId1 = userByUsername.getUserId();
//
//        userId1 = userId;
//
//        Cart cart = cartService.saveCart(userId);
//
//        Integer cartId = cart.getCartId();
//
//        cartService.findById(cartId);
//
//        httpSession.setAttribute("cartId", cartId);
//
//        model.addAttribute("cartDTO", cartDTO);
//
//        return "redirect:/user/home";
//    }

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

        Integer cartId = (Integer) httpSession.getAttribute("cartId");

        Cart cart = cartService.findById(cartId);


        model.addAttribute("cart", cart);
        model.addAttribute("cartDTO", cartDTO);

        return "cart_details";
    }

    @PostMapping(value = "/cart/{isbn}/add")
    public String addToCartPage(
            @PathVariable String isbn,
            @ModelAttribute("cartDTO")
            CartDTO cartDTO,
            @ModelAttribute("cartItemDTO")
            CartItemDTO cartItemDTO,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId = userByUsername.getUserId();

        Cart cartByUserId = cartService.findCartByUserId(userId);

        Books byIsbn = booksService.findByIsbn(isbn);

        cartService.addItemToCart(cartByUserId, byIsbn);

        return "redirect:/book/list";
    }

    @GetMapping(value = "/cart/make-reservation")
    public String reservationPage(
            @ModelAttribute("reservations")
            Reservations reservations,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);

        Integer userId = userByUsername.getUserId();

        Cart cartByUserId = cartService.findCartByUserId(userId);

        model.addAttribute("cart", cartByUserId);
        model.addAttribute("username", username);
        model.addAttribute("reservations", reservations);

        return "cart_make_reservation";
    }

    @PostMapping(value = "/cart/make-reservation")
    public String reservation(
            HttpSession httpSession
    ) {
        Integer cartId = (Integer) httpSession.getAttribute("cartId");

        Cart cart = cartService.findById(cartId);

        reservationsService.makeReservation(cart);

        return "redirect:/cart/reservation/details/{userId}";
    }

    @GetMapping(value = "/cart/reservation/list/{userId}")
    public String reservationListPage(
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

        model.addAttribute("reservationsList", reservationsList);

        return "cart_reservation_list_by_user";
    }

    @GetMapping(value = "/cart/reservation/details/{reservationId}")
    public String reservationDetailsPage(
            @PathVariable("reservationId")
            Integer reservationId,
            @ModelAttribute("cartDTO")
            CartDTO cartDTO,
            Model model
    ) {

        Reservations reservation = reservationsService.findById(reservationId);

        model.addAttribute("reservation", reservation);

        return "reservation_to_loan_details";
    }
//
//    @GetMapping(value = "/cart/reservation/loan/request/{reservationId}")
//    public String loanRequestPage(
//            @PathVariable("reservationId")
//            Integer reservationId,
//            @ModelAttribute("loanRequestDTO")
//            LoanRequestDTO loanRequestDTO,
//            Model model
//    ) {
//        Reservations reservation = reservationsService.findById(reservationId);
//        Integer reservationId1 = reservation.getReservationId();
//
//        reservationId1 = reservationId;
//
//        model.addAttribute("loanRequestDTO", loanRequestDTO);
//
//        return "cart_loan_request_from_reservation";
//    }

    @PostMapping(value = "/cart/reservation/loan/request/{reservationId}")
    public String loanRequest(
            @PathVariable("reservationId")
            Integer reservationId,
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model,
            HttpSession httpSession
    ) {
        Reservations reservation = reservationsService.findById(reservationId);
        Integer reservationId1 = reservation.getReservationId();

        reservationId1 = reservationId;

        loanRequestService.makeLoanRequestFromReservation(reservation);


        model.addAttribute("reservationDTO", reservationsDTO);

        return "loan_request_from_reservation_success";
    }

    @GetMapping(value = "/cart/loan/request")
    public String loanPage(
            @ModelAttribute("cartDTO")
            CartDTO cartDTO,
            Model model,
            HttpSession httpSession
    ) {
        Integer cartId = (Integer) httpSession.getAttribute("cartId");

        Cart cart = cartService.findById(cartId);

        model.addAttribute("cart", cart);
        model.addAttribute("cartDTO", cartDTO);

        return "loan_from_cart";
    }

    @PostMapping(value = "/cart/loan/request")
    public String loan(
            @ModelAttribute("CartDTO")
            CartDTO cartDTO,
            Model model,
            HttpSession httpSession
    ) {
        Integer cartId = (Integer) httpSession.getAttribute("cartId");

        Cart cart = cartService.findById(cartId);

        loanRequestService.makeLoanRequestFromCart(cart);

        model.addAttribute("cartDTO", cartDTO);

        return "redirect:/user/home";
    }


}
