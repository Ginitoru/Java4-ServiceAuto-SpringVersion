package com.gini.iordache.controllers.auto;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LaborOrderController {


    @GetMapping("/laborOrderPage")
    public String getLaborOrderPage(){


        return "order/laborOrder-page";
    }


}
