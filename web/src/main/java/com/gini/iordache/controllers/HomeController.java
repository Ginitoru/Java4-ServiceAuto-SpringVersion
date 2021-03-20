package com.gini.iordache.controllers;


import com.gini.iordache.services.ServiceOrderService;
import com.gini.iordache.services.impl.utility.AllOrdersIdAndStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@AllArgsConstructor
@Controller
public class HomeController {


    private final ServiceOrderService serviceOrderService;
    private final AllOrdersIdAndStatus allOrdersIdAndStatus;







    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/main")
    public String intra(Model model){

        model.addAttribute("serviceOrderIdAndStatus", allOrdersIdAndStatus.getList());

        return "main-page";
    }

}
