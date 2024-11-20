package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.library.api.dto.CartDTO;
import org.example.library.business.CartService;
import org.example.library.business.UsersService;
import org.example.library.domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UsersService usersService;


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

    @GetMapping(value = "/cart/details")
    public String cartDetailsPage(
            Model model,
            HttpSession httpSession
    ) {


        //@TODO dokończyć metodę
        return "cart_details";
    }


}
