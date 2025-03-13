package org.example.library.api.controller;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.AuthorsDTO;
import org.example.library.business.AuthorsService;
import org.example.library.domain.Authors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthorsController {

    private final AuthorsService authorsService;

    @GetMapping(value = "/employee/author/home")
    public String home() {

        return "employee_author_home";
    }

    @GetMapping(value = "/employee/author/add")
    public String addAuthorPage(
            @ModelAttribute("authorsDTO")
            AuthorsDTO authorsDTO,
            Model model
    ) {
        model.addAttribute("authorsDTO", authorsDTO);

        return "add_author";
    }

    @PostMapping(value = "/employee/author/add")
    public String addAuthor(
            @ModelAttribute("authorsDTO")
            AuthorsDTO authorsDTO,
            Model model
    ) {
        authorsService.saveAuthor(authorsDTO);

        model.addAttribute("authorsDTO", authorsDTO);

        return "redirect:/library/employee/home";
    }

    @GetMapping(value = "/employee/author/list")
    public String listAuthors(
            Model model,
            @ModelAttribute("authorsDTO")
            AuthorsDTO authorsDTO
    ) {
        List<Authors> all = authorsService.findAll();

        model.addAttribute("authors", all);

        return "list_authors";
    }

    @GetMapping(value = "/employee/author/update/{authorCode}")
    public String updateAuthorPage(
            @PathVariable("authorCode")
            String authorCode,
            Model model
    ) {
        Authors author = authorsService.findByAuthorCode(authorCode);

        model.addAttribute("author", author);

        return "update_author";
    }

    @PatchMapping(value = "/employee/author/update/{authorCode}")
    public String updateAuthor(
            @PathVariable("authorCode")
            String authorCode,
            @ModelAttribute("authorsDTO")
            AuthorsDTO authorsDTO,
            Model model
    ) {
        Authors author = authorsService.findByAuthorCode(authorCode);
        authorsService.updateAuthor(author, authorsDTO);

        model.addAttribute("author", author);

        return "redirect:/employee/home";
    }
}
