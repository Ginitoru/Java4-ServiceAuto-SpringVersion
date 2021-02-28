package com.gini.iordache.controllers;

import com.gini.iordache.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {


    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("user", new User());
        return "user/login-user";
    }


    @PostMapping("/login-processing")
    public String postLogin(){
        return "redirect:/intra";
    }

}
