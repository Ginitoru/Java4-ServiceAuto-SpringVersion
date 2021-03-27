package com.gini.iordache.controllers.client;

import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.services.interfaces.CompanyService;
import com.gini.iordache.services.interfaces.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/clients")
public class ClientController {




    private final PersonService personService;
    private final CompanyService companyService;



    @GetMapping("/client")
    public String getClientPage(Model model){

        changeFlags(false, false, model);

        return "client/client-page";
    }



    @GetMapping("/personForm")
    public String showPersonForm(Model model){

        model.addAttribute("person", new Person());
        changeFlags(true, false, model);
        return "client/client-page";
    }



    @GetMapping("/companyForm")
    public String showCompanyForm(Model model){

        model.addAttribute("company", new Company());
        changeFlags(false, true, model);
        return "client/client-page";
    }




    @PostMapping("/create-person")
    public String createPerson(@Valid @ModelAttribute("person") Person person,
                                            BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            changeFlags(true, false, model);
            System.out.println(bindingResult.toString());
            return "client/client-page";
        }


        changeFlags(false, false, model);
        personService.createPerson(person);
        return "redirect:/clients/client";

    }



    @PostMapping("/create-company")
    public String createCompany(@Valid @ModelAttribute("company") Company company,
                                            BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            changeFlags(false, true, model);
            System.out.println(bindingResult.toString());
            return "client/client-page";
        }

        changeFlags(false, false, model);
        companyService.createCompany(company);
        return "redirect:/clients/client";
    }


    private void changeFlags(boolean flag1, boolean flag2, Model model){
        model.addAttribute("flag1", flag1);
        model.addAttribute("flag2", flag2);

    }

}
