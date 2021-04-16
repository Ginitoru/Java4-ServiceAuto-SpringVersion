package com.gini.iordache.controllers.order;


import com.gini.iordache.controllers.MiniCache;
import com.gini.iordache.controllers.home.HomeController;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.impl.order.LaborOrderServiceImpl;
import com.gini.iordache.services.interfaces.LaborService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
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

@AllArgsConstructor
@Controller
@RequestMapping("/laborOrder")
public class LaborOrderController {


    private final MiniCache miniCache;
    private final LaborOrderServiceImpl laborOrderService;


    @GetMapping("/laborOrderPage")
    public String getLaborOrderPage(Model model){

        miniCache.loadLaborsOrder();

        model.addAttribute("labors", miniCache.getLabors());
        model.addAttribute("orderLabors",miniCache.getLaborFromOrder());


        return "order/laborOrder-page";
    }


    @GetMapping("/searchLabor")
    public String searchLabor(HttpServletRequest request, Model model){

        var laborDescription = request.getParameter("laborDescription");
        miniCache.loadLabors(laborDescription);


        model.addAttribute("labors", miniCache.getLabors());
        model.addAttribute("orderLabors", miniCache.getLaborFromOrder());



        return "order/laborOrder-page";
    }


    @GetMapping("/addLaborToOrder")
    public String addLaborToOrder(HttpServletRequest request, Model model){


       ServiceOrder serviceOrder = miniCache.getCompleteServiceOrder();

        int id = Integer.parseInt(request.getParameter("laborId"));

        miniCache.getLabors().stream()
                                .filter(u -> u.getId() == id)
                                .findFirst()
                                .ifPresent(l -> laborOrderService.addLaborToServiceOrder(l, serviceOrder));

        return "redirect:/laborOrder/laborOrderPage";
    }

    @PostMapping("/deleteLabor")
    public String deleteLaborOrder(HttpServletRequest request){

        var id = Integer.parseInt(request.getParameter("laborId"));

        laborOrderService.deleteLaborFromOrder(id);

        return "redirect:/laborOrder/laborOrderPage";
    }


}
