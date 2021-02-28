package com.gini.iordache.controllers;

import com.gini.iordache.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {


    @GetMapping("/login")
    public String showLoginPage(Model model){

        model.addAttribute("user", new User());
        return "user/login-user";
    }


    @PostMapping("/login-processing")
    public String loginProcessing(){
        return "redirect:/intra";
    }

    @GetMapping("/create-user")
    public String showCreateUserPage(Model model){

        model.addAttribute("newUser", new User());

        return "user/create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("newUser") User user){


        return "redirect:/login";
    }

}
