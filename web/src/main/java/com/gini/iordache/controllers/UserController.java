package com.gini.iordache.controllers;

import com.gini.iordache.entity.user.Authorities;
import com.gini.iordache.entity.user.User;
import com.gini.iordache.services.UserService;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;


@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login-user";
    }


    @PostMapping("/login-processing")
    public String loginProcessing() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

            return "redirect:/login";
        }

        return "redirect:/intra";
    }


    @GetMapping("/create-user")
    public String showCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("authority", Authorities.values());
        return "user/create-user";
    }


    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/activate")
    public String activateAccount(HttpServletRequest request, Model model) {

        var token = request.getParameter("token");
        var email = request.getParameter("email");

        System.out.println("token " + token + " username " + email);

        model.addAttribute("pacpac", token);
        model.addAttribute("email", email);


        return "user/activate-user";
    }

}


