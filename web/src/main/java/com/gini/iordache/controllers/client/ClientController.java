package com.gini.iordache.controllers.client;

import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.services.interfaces.CompanyService;
import com.gini.iordache.services.interfaces.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/clients")
public class ClientController {


    private final PersonService personService;
    private final CompanyService companyService;


    @GetMapping("/client")
    public String getClientPage(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("company", new Company());

        return "client/client-page";
    }



    @GetMapping("/personForm")
    public String showPersonForm(Model model){
        return "redirect:/clients/client?person";
    }



    @GetMapping("/companyForm")
    public String showCompanyForm(){
        return "redirect:/clients/client?company";
    }



    @PostMapping("/create-person")
    public String createPerson(@ModelAttribute("person") Person person){
        personService.createPerson(person);
        return "redirect:/clients/client";
    }



    @PostMapping("/create-company")
    public String createCompany(@ModelAttribute("company") Company company){
        companyService.createCompany(company);
        return "redirect:/clients/client";
    }

}
