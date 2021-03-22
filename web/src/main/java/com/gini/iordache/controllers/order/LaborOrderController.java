package com.gini.iordache.controllers.order;


import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.services.interfaces.LaborService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/laborOrder")
public class LaborOrderController {

    private final LaborService laborService;

    List<Labor> labors = new ArrayList<>();



    @Autowired
    public LaborOrderController(LaborService laborService) {
        this.laborService = laborService;
    }

    @GetMapping("/laborOrderPage")
    public String getLaborOrderPage(Model model){

        model.addAttribute("labors", labors);


        return "order/laborOrder-page";
    }


    @GetMapping("/searchLabor")
    public String searchLabor(HttpServletRequest request, Model model){



        var laborDescription = request.getParameter("laborDescription");

        labors.clear();                                                 // :-> resetam lista pentru urmatorul find

        labors = laborService.findLaborByName(laborDescription);
        model.addAttribute("labors", labors);

        System.out.println(labors);


        return "redirect:/laborOrder/laborOrderPage";
    }


    @GetMapping("/addLaborToOrder")
    public String addLaborToOrder(HttpServletRequest request){

        int id = Integer.parseInt(request.getParameter("laborId"));

        labors.stream()
                .filter(u -> u.getId() == id)
                .findAny();


        return "redirect:/laborOrder/laborOrderPage";
    }



}
