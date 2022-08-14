package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.validation.Valid;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        int result = 0;
        if (!bindingResult.hasErrors()) {
            if (!userService.isUsernameAvailable(user.getUsername())) {
                model.addAttribute("signupError", "Username exist");
            } else {
                result = userService.signup(user);
            }
        } else {
            model.addAttribute("signupError", "Validation failed");
            model.addAttribute("validationError", bindingResult.getAllErrors());
        }
        model.addAttribute("signupSuccess", result == 1 ? true : false);
        return "/signup";
    }

}
