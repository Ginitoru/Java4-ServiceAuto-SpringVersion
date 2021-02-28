package com.gini.iordache.controllers;

import com.gini.iordache.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Test2Controller {


    @GetMapping("/login")
    public String login(Model model){


        model.addAttribute("user", new User());

        return "login-user";
    }

    @PostMapping("/login-processing")
    public String postLogin(){

        return "redirect:/intra";
    }

    @GetMapping("/")
    public String index(){

        return "index";
    }


    @GetMapping("/intra")
    public String intra(){

        return "intra";
    }

}
