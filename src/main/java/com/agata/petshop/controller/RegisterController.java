package com.agata.petshop.controller;

import com.agata.petshop.model.User;
import com.agata.petshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User userForm) {

        userService.save(userForm);
        userService.login(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/home";
    }

    public RegisterController(UserService userService) {
        this.userService = userService;
    }
}
