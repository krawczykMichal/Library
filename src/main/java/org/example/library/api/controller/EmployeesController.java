package org.example.library.api.controller;

import lombok.AllArgsConstructor;
import org.example.library.business.EmployeesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping(value = "/library/employee/home")
    public String home() {
        return "library_employee_home";
    }
}
