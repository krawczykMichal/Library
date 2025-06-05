package org.example.library.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";

    @GetMapping(value = HOME)
    public String homePage() {
        System.out.println("gitlabCI działa");
        return "home";
    }

    @GetMapping(value = "/employee/login")
    public String libraryEmployeePage() {
        return "library_employee";
    }
}
