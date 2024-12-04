package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.EmployeesDTO;
import org.example.library.api.dto.LoanRequestDTO;
import org.example.library.api.dto.LoansDTO;
import org.example.library.business.EmployeesService;
import org.example.library.business.LoanRequestService;
import org.example.library.business.LoansService;
import org.example.library.domain.Employees;
import org.example.library.domain.LoanRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;
    private final LoansService loansService;
    private final LoanRequestService loanRequestService;


    @GetMapping(value = "/employee/home")
    public String home(
            Authentication authentication,
            Model model,
            HttpSession httpSession
    ) {
        authentication.isAuthenticated();
        log.debug("log authentication is: " + authentication);
        Object prin = authentication.getPrincipal();
        log.debug("log principal is instance of: " + prin.getClass());

        String username = getEmailFromAuthentication(authentication);

        httpSession.setAttribute("username", username);

        return "library_employee_home";
    }

    private String getEmailFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();
    }

    @GetMapping(value = "/admin/employee/create")
    public String createPage(
            @ModelAttribute("employeesDTO")
            EmployeesDTO employeesDTO,
            Model model
    ) {
        model.addAttribute("employeesDTO", employeesDTO);

        return "admin_employee_create";
    }

    @PostMapping(value = "/admin/employee/create")
    public String create(
            @ModelAttribute("employeesDTO")
            EmployeesDTO employeesDTO,
            Model model,
            HttpSession httpSession
    ) {
        employeesService.saveEmployee(employeesDTO);

        model.addAttribute("employeesDTO", employeesDTO);

        return "admin_home";
    }

    @GetMapping(value = "/employee/cart/loanRequest-list")
    public String loanPage(
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            Model model
    ) {
        loanRequestService.findAll();

        model.addAttribute("loanRequestDTO", loanRequestDTO);

        return "cart_loan_list";
    }

    @GetMapping(value = "/employee/cart/loanRequest/details/{loanRequestId}")
    public String loanRequestDetails(
            @ModelAttribute("loanRequestDTO")
            LoanRequestDTO loanRequestDTO,
            @PathVariable("loanRequestId")
            Integer loanRequestId,
            Model model
    ) {
        loanRequestService.findById(loanRequestId);

        model.addAttribute("loanRequestDTO", loanRequestDTO);

        return "cart_loan_request_details";
    }

    @PostMapping(value = "/employee/cart/loanRequest/details/{loanRequestId}")
    public String loan(
            @ModelAttribute("loansDTO")
            LoansDTO loansDTO,
            @PathVariable("loanRequestId")
            Integer loanRequestId,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();

        Employees employee = employeesService.findByUsername(username);
        LoanRequest loanRequest = loanRequestService.findById(loanRequestId);


        loansService.makeLoan(loanRequest, employee);

        model.addAttribute("loansDTO", loansDTO);
        //@TODO dostęp do wypożyczenia musi mieć użytkownik aby mógł sprawdzać do kiedy ma wypożyczone jakie książki, trzeba ustalić jak to dokładnie robić. sprawdzić bazę danych i relacje gdzie user powinien jeszcze się znaleźć i poprawić to aby prośba o wypożyczenie pojawiała się z danymi użytkownika

        return "redirect:/employee/cart/loanRequest-list";
    }
}
