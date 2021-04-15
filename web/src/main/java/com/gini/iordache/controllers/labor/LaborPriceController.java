package com.gini.iordache.controllers.labor;

import com.gini.errors.labor.InvalidPriceException;
import com.gini.iordache.entity.labor.LaborPrice;
import com.gini.iordache.services.interfaces.LaborPriceService;
import com.gini.iordache.utility.LaborCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/prices")
public class LaborPriceController {

    private final LaborPriceService laborPriceService;


    @GetMapping("/showPrices")
    public String showLaborPricePage(Model model){

        LaborPrice laborPrice = laborPriceService.findAllPrices();



        model.addAttribute("price", new LaborPrice());
        model.addAttribute("laborPrices", laborPrice);
        model.addAttribute("category", LaborCategory.values());

        System.out.println(laborPrice);

        return "/labor/labor-price";
    }


    @PostMapping("/setPrices")
    public String setLaborPrices(@Valid @ModelAttribute("price") LaborPrice laborPrice, BindingResult bindingResult, Model model){



        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
            model.addAttribute("laborPrices", new LaborPrice());     //ca sa nu apara pretul in pagina cand bag nr negativ
            model.addAttribute("category", LaborCategory.values());

            return "/labor/labor-price";

        }
        laborPriceService.createAllLaborPrices(laborPrice);

        return "redirect:/prices/showPrices";
    }


    @PostMapping("/updatePrice")
    public String updatePrice(HttpServletRequest request){

        try{

            var categoryPrice = request.getParameter("category");
            var newPrice = Double.parseDouble(request.getParameter("updatePrice"));
            laborPriceService.updatePrices(newPrice, categoryPrice);
        }catch(NumberFormatException e){
            e.printStackTrace();
            throw new InvalidPriceException("wrong format number");

        }


        return "redirect:/prices/showPrices";
    }







}
