package com.gini.iordache.controllers.auto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/serviceOrder")
public class ServiceOrderController {


    @GetMapping("/serviceOrder")
    public String showServiceOrderPage(){

        return "auto/serviceOrder-page";
    }





}
