package org.example.library.api.controller;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.AuthorsDTO;
import org.example.library.business.AuthorsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthorsController {

    private final AuthorsService authorsService;

    @GetMapping(value = "/author/add")
    public String addAuthorPage(
            @ModelAttribute("authorsDTO")
            AuthorsDTO authorsDTO,
            Model model
    ) {
        model.addAttribute("authorsDTO", authorsDTO);

        return "add_author";
    }

    @PostMapping(value = "/author/add")
    public String addAuthor(
            @ModelAttribute("authorsDTO")
            AuthorsDTO authorsDTO,
            Model model
    ) {
        authorsService.saveAuthor(authorsDTO);

        model.addAttribute("authorsDTO", authorsDTO);

        return "redirect:/library/employee/home";
    }
}
