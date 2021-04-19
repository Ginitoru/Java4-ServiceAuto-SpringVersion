package com.gini.iordache.controllers.order;



import com.gini.errors.order.SelectOrderException;
import com.gini.iordache.cache.MiniCache;
import com.gini.iordache.entity.order.ServiceOrder;

import com.gini.iordache.services.interfaces.LaborOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
@RequestMapping("/laborOrder")
public class LaborOrderController {


    private final MiniCache miniCache;
    private final LaborOrderService laborOrderService;


    @GetMapping("/laborOrderPage")
    public String getLaborOrderPage(Model model){

        if(miniCache.getCompleteServiceOrder() == null){
                throw new SelectOrderException("No order selected!");
        }

        miniCache.loadLaborsOrder();

        model.addAttribute("labors", miniCache.getLabors());
        model.addAttribute("orderLabors",miniCache.getLaborFromOrder());
        model.addAttribute("order", miniCache.getCompleteServiceOrder()); // pt javascript din laborOrder-page.html

        return "order/laborOrder-page";
    }


    @GetMapping("/searchLabor")
    public String searchLabor(HttpServletRequest request, Model model){

        var laborDescription = request.getParameter("laborDescription");
        miniCache.loadLabors(laborDescription);

        return "redirect:/laborOrder/laborOrderPage";
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

        laborOrderService.deleteLaborFromOrder(id, miniCache.getCompleteServiceOrder());

        return "redirect:/laborOrder/laborOrderPage";
    }


}
