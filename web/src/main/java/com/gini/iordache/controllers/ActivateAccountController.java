package com.gini.iordache.controllers;

import com.gini.iordache.entity.ActivationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActivateAccountController {


    @GetMapping("/token")
    public String showExpiredTokenPage(Model model){

        model.addAttribute("token", new ActivationToken());

        return "activation/resend-token";
    }

    @PostMapping("/token")
    public String activateExpiredToken(@ModelAttribute("token") ActivationToken activationToken){



        return "redirect:/login";
    }


}
