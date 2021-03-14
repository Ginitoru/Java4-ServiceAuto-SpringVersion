package com.gini.iordache.controllers.auto;

import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientController {


    @GetMapping("/client")
    public String getClientPage(Model model){


        model.addAttribute("person", new Person());
        model.addAttribute("company", new Company());

        return "client/client-page";
    }


    @PostMapping("/create-person")
    public String createPerson(@ModelAttribute("person") Person person){




        return "redirect:/clients/client";
    }


    @PostMapping("/create-company")
    public String createCompany(@ModelAttribute("company") Company company){



        return "redirect:/clients/client";
    }

}
