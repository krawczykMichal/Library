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
    private final CartItemService cartItemService;


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

    @GetMapping(value = "/user/cart/{userId}/details")
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
        if (cart == null) {
            cartService.saveCart(userByUsername);
            cartService.findCartByUserId(userId);
        }


        model.addAttribute("cart", cart);
        model.addAttribute("cartDTO", cartDTO);

        return "cart_details";
    }

    @PostMapping(value = "/user/cart/{isbn}/add")
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
        Integer bookId = byIsbn.getBookId();

        cartService.addItemToCart(cartByUserId, byIsbn);

        return "redirect:/book/list";
    }

    @GetMapping(value = "/user/cart/make-reservation")
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
        List<CartItem> cartItem = cartByUserId.getCartItem();
        System.out.println("cartItem: " + cartItem);


        model.addAttribute("cart", cartByUserId);
        model.addAttribute("username", username);
        model.addAttribute("reservations", reservations);

        return "cart_make_reservation";
    }

    @PostMapping(value = "/user/cart/make-reservation")
    public String reservation(
            HttpSession httpSession
    ) {
        Integer cartId = (Integer) httpSession.getAttribute("cartId");

        Cart cart = cartService.findById(cartId);
        List<CartItem> cartItems = cart.getCartItem();

        reservationsService.makeReservation(cart, cartItems);
        cartItemService.clearCartAfterReservationOrLoan(cart.getCartId());
        return "redirect:/home";
    }

    @GetMapping(value = "/employee/cart/reservation/list/{userId}")
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

    @GetMapping(value = "/user/cart/reservation/details/{reservationNumber}")
    public String reservationDetailsPage(
            @PathVariable("reservationNumber")
            String reservationNumber,
            @ModelAttribute("cartDTO")
            CartDTO cartDTO,
            Model model
    ) {

        Reservations reservation = reservationsService.findByReservationNumber(reservationNumber);

        model.addAttribute("reservation", reservation);

        return "users_reservations_list";
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

    @PostMapping(value = "/user/cart/reservation/loan/request/{reservationNumber}")
    public String loanRequest(
            @PathVariable("reservationNumber")
            String reservationNumber,
            @ModelAttribute("reservationsDTO")
            ReservationsDTO reservationsDTO,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Users userByUsername = usersService.findByUsername(username);
        Reservations reservation = reservationsService.findByReservationNumber(reservationNumber);

        loanRequestService.makeLoanRequestFromReservation(reservation, userByUsername);
        reservationsService.deleteByReservationNumber(reservationNumber);
        model.addAttribute("reservationDTO", reservationsDTO);

        return "loan_request_from_reservation_success";
    }

    @GetMapping(value = "/user/cart/loan/request")
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

    @PostMapping(value = "/user/cart/loan/request")
    public String loan(
            @ModelAttribute("CartDTO")
            CartDTO cartDTO,
            Model model,
            HttpSession httpSession
    ) {
        Integer cartId = (Integer) httpSession.getAttribute("cartId");

        Cart cart = cartService.findById(cartId);
        List<CartItem> cartItemList = cart.getCartItem();

        loanRequestService.makeLoanRequestFromCart(cart, cartItemList);
        cartService.clearCart(cart.getCartId());
        // @TODO usunąć loanRequestItem po tym jak loanRequest przechodzi w loan, tak w sumie to sprawdzić czy można usunąć cały loanRequest kiedy zamieni się on w loan


        model.addAttribute("cartDTO", cartDTO);

        return "redirect:/user/home";
    }


}
