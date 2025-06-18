package com.LonpacInsurance.controller;

import com.LonpacInsurance.service.UserNotFoundException;
import com.LonpacInsurance.service.UserService;
import com.LonpacInsurance.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> userList = service.listAll();
        model.addAttribute("userList", userList);

        return "users";
    }

    @GetMapping("/users/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("userAdd", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "addUser";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        try {
            service.save(user);
            ra.addFlashAttribute("message", "The user has been saved successfully.");
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry") && e.getMessage().contains("ic_num")) {
                ra.addFlashAttribute("message", "IC Number is already registered.");
            } else {
                ra.addFlashAttribute("message", "An error occurred during save due to data integrity. Please check your input.");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("message", "An unexpected error occurred: " + e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("userAdd", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            ra.addFlashAttribute("message", "The user has been updated successfully. ");

            return "addUser";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user has been deleted successfully. ");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}