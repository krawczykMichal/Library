package org.example.library.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.EmployeesDTO;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.EmployeesService;
import org.example.library.domain.Employees;
import org.example.library.domain.User;
import org.example.library.domain.Users;
import org.example.library.domain.exception.UserNameAlreadyTakenException;
import org.example.library.domain.exception.ValidationException;
import org.example.library.infrastructure.security.business.dao.UserDao;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class AdminController {

    private final EmployeesService employeesService;
    private final UserDao userDao;


    @GetMapping(value = "/admin/home")
    public String home(
            Authentication authentication,
            @ModelAttribute("employeeDTO")
            EmployeesDTO employeeDTO,
            Model model,
            HttpSession httpSession
    ) {

        authentication.isAuthenticated();
        log.debug("log authentication is: " + authentication);
        Object prin = authentication.getPrincipal();
        log.debug("log principal is instance of: " + prin.getClass());

        String username = getUsernameFromAuthentication(authentication);

        User user = userDao.findByUsername(username);

        httpSession.setAttribute("username", username);
        httpSession.setAttribute("user", user);
        model.addAttribute("employeeDTO", employeeDTO);

        return "admin_home";
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();
    }

    @GetMapping(value = "/admin/employee/create")
    public String createPage(
            @ModelAttribute("employeesDTO")
            EmployeesDTO employeesDTO,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("employeesDTO", employeesDTO);  // Przekazujemy dane do widoku
        }

        if (model.containsAttribute("errorUsername")) {
            String errorMessage = (String) model.getAttribute("errorUsername");
            model.addAttribute("errorUsername", errorMessage);
        }

        model.addAttribute("employeesDTO", employeesDTO);

        return "admin_employee_create";
    }

    @PostMapping(value = "/admin/employee/create")
    public String create(
            @ModelAttribute("employeesDTO")
            EmployeesDTO employeesDTO,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (result.hasErrors()) {

            model.addAttribute("employeesDTO", employeesDTO);
            return "admin_employee_create";
        }

        Employees employees = employeesService.findByUsername(employeesDTO.getUsername());
        if (employees != null) {
            redirectAttributes.addFlashAttribute("errorUsername", "Username is already taken!");
            return "redirect:/admin/employee/create";
        }
        employeesService.saveEmployee(employeesDTO);

        model.addAttribute("employeesDTO", employeesDTO);

        return "redirect:/admin/home";
    }

    @GetMapping(value = "/admin/employee/list")
    public String employeeList(
            Model model
    ) {
        List<Employees> employeesList = employeesService.findAll();

        model.addAttribute("employeesList", employeesList);

        return "admin_employee_list";
    }

    @GetMapping(value = "/admin/employee/{employeeNumber}/details")
    public String employeeDetails(
            @PathVariable("employeeNumber")
            String employeeNumber,
            Model model
    ) {

        Employees byEmployeeNumber = employeesService.findByEmployeeNumber(employeeNumber);

        model.addAttribute("employeesDTO", byEmployeeNumber);

        return "admin_employee_details";
    }


    @GetMapping(value = "/admin/employee/{employeeNumber}/update")
    public String updateEmployeePage(
            @PathVariable("employeeNumber")
            String employeeNumber,
            Model model
    ) {

        Employees byEmployeeNumber = employeesService.findByEmployeeNumber(employeeNumber);

        if (model.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", model.getAttribute("errorMessage"));
        }

        model.addAttribute("employeesDTO", byEmployeeNumber);

        return "admin_employee_update";
    }

    @PatchMapping(value = "/admin/employee/{employeeNumber}/update")
    public String updateEmployee(
            @PathVariable("employeeNumber")
            String employeeNumber,
            @ModelAttribute("employeesDTO")
            EmployeesDTO employeesDTO,
            Model model,
            RedirectAttributes redirectAttributes

    ) {

        try {
            employeesService.updateEmployee(employeeNumber, employeesDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        } catch (UserNameAlreadyTakenException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/employee/{employeeNumber}/update";
        } catch (ValidationException e) { // Obsługa błędów walidacji
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/employee/{employeeNumber}/update";
        }

        model.addAttribute("employeesDTO", employeesDTO);

        return "redirect:/admin/home";
    }

    @DeleteMapping(value = "/admin/employee/{employeeNumber}/delete")
    public String deleteEmployee(
            @PathVariable("employeeNumber")
            String employeeNumber,
            Model model
    ) {
        employeesService.deleteByEmployeeNumber(employeeNumber);

        return "redirect:/admin/home";
    }


}
