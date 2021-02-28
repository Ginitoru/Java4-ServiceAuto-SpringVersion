package com.gini.iordache.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomePage {

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/intra")
    public String intra(){

        return "intra";
    }

}
