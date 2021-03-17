package com.gini.iordache.controllers.labor;

import com.gini.iordache.entity.labor.LaborPrice;
import com.gini.iordache.services.LaborPriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
@RequestMapping("/prices")
public class LaborPriceController {

    private final LaborPriceService laborPriceService;


    @GetMapping("/showPrices")
    public String showLaborPricePage(Model model){

        LaborPrice laborPrice = laborPriceService.findAllPrices();



        model.addAttribute("laborPrices", laborPrice);

        return "/labor/labor-price";
    }


    @PostMapping("/setPrices")
    public String setLaborPrices(HttpServletRequest request){


        return "redirect:/showPrices";
    }







}
