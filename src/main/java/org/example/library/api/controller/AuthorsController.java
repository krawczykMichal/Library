package org.example.library.api.controller;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.AuthorsDTO;
import org.example.library.business.AuthorsService;
import org.example.library.domain.Authors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/author/update/{authorId}")
    public String updateAuthorPage(
            @PathVariable("authorId")
            Integer authorId,
            Model model
    ) {
        Authors author = authorsService.findById(authorId);

        model.addAttribute("author", author);

        return "update_author";
    }

    @PatchMapping(value = "/author/update/{authorId}")
    public String updateAuthor(
            @PathVariable("authorId")
            Integer authorId,
            @ModelAttribute("authorsDTO")
            AuthorsDTO authorsDTO,
            Model model
    ) {
        Authors author = authorsService.findById(authorId);
        authorsService.updateAuthor(author, authorsDTO);

        model.addAttribute("author", author);

        return "redirect:/employee/home";
    }
}
