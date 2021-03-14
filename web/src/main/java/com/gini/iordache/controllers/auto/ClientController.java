package com.gini.iordache.controllers.auto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientController {


    @GetMapping("/client")
    public String getClientPage(){

        return "app/part-page";
    }

}
