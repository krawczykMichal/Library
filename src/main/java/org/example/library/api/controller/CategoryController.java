package org.example.library.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.CategoriesDTO;
import org.example.library.business.CategoriesService;
import org.example.library.business.EmployeesService;
import org.example.library.domain.Categories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class CategoryController {

    private final EmployeesService employeesService;
    private final CategoriesService categoriesService;

    @GetMapping(value = "/employee/category/home")
    public String home() {

        return "employee_category_home";
    }

    @GetMapping(value = "/employee/category/list")
    public String list(
            Model model
    ) {
        List<Categories> all = categoriesService.findAll();

        model.addAttribute("categories", all);

        return "employee_category_list";
    }

    @GetMapping(value = "/employee/category/add")
    public String addCategoryPage(
            Model model,
            @ModelAttribute("categoriesDTO")
            CategoriesDTO categoriesDTO
    ) {

        model.addAttribute("categoriesDTO", categoriesDTO);

        return "employee_category_add";
    }
    @PostMapping(value = "/employee/category/add")
    public String addCategory(
            Model model,
            @ModelAttribute("categoriesDTO")
            CategoriesDTO categoriesDTO
    ) {
        categoriesService.addCategory(categoriesDTO);

        model.addAttribute("categoriesDTO", categoriesDTO);

        return "redirect:/employee/category/home";
    }

    @GetMapping(value = "/employee/category/{name}/details")
    public String categoryName(
            Model model,
            @PathVariable String name
    ) {
        Categories byName = categoriesService.findByName(name);

        model.addAttribute("categories", byName);

        return "employee_category_details";
    }

    @PatchMapping(value = "/employee/category/{name}/details")
    public String updateCategory(
            Model model,
            @PathVariable String name,
            @ModelAttribute("categoriesDTO")
            CategoriesDTO categoriesDTO
    ) {
        Categories byName = categoriesService.findByName(name);

        Categories changed = categoriesService.updateCategoryName(byName, categoriesDTO);

        model.addAttribute("categories", changed);

        return "redirect:/employee/category/home";
    }
}
