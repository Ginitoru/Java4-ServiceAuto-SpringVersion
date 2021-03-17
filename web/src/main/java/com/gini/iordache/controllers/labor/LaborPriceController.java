package com.gini.iordache.controllers.labor;

import com.gini.iordache.entity.labor.LaborPrice;
import com.gini.iordache.services.LaborPriceService;
import com.gini.iordache.utility.LaborCategory;
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
        model.addAttribute("category", LaborCategory.values());

        return "/labor/labor-price";
    }


    @PostMapping("/setPrices")
    public String setLaborPrices(HttpServletRequest request){

        var mechanicalLaborPrice = Double.parseDouble(request.getParameter("mechanicalLaborPrice"));
        var electricalLaborPrice = Double.parseDouble(request.getParameter("electricalLaborPrice"));
        var normalLaborPrice = Double.parseDouble(request.getParameter("normalLaborPrice"));
        var itpDieselEnginePrice = Double.parseDouble(request.getParameter("itpDieselEnginePrice"));
        var itpGasolineEnginePrice = Double.parseDouble(request.getParameter("itpGasolineEnginePrice"));
        var itpSuvPrice = Double.parseDouble(request.getParameter("itpSuvPrice"));
        var itpTruckPrice = Double.parseDouble(request.getParameter("itpTruckPrice"));


        System.out.println(mechanicalLaborPrice + " DDDDDDDDDDDDDDDDDDDDDDDDDD");
        LaborPrice laborPrice = new LaborPrice();

        laborPrice.setMechanicalLaborPrice(mechanicalLaborPrice);
        laborPrice.setElectricalLaborPrice(electricalLaborPrice);
        laborPrice.setNormalLaborPrice(normalLaborPrice);
        laborPrice.setItpDieselEnginePrice(itpDieselEnginePrice);
        laborPrice.setItpGasolineEnginePrice(itpGasolineEnginePrice);
        laborPrice.setItpSuvPrice(itpSuvPrice);
        laborPrice.setItpTruckPrice(itpTruckPrice);

        laborPriceService.createAllLaborPrices(laborPrice);

        return "/labor/labor-price";
    }


    @GetMapping("/refresh")
    public String refreshButton(){
        return "redirect:/prices/showPrices";
    }







}
