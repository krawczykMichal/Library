package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.library.api.dto.BooksDTO;
import org.example.library.api.dto.CartDTO;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartService cartService;


    // @TODO stworzyć tutaj metody że użytkownik tworzy koszyk i dodaje elementy , może wybrać rezerwacje lub wypożyczenie i wtedy elementy z koszyka zostają usunięte ale koszyk zostaje
}
