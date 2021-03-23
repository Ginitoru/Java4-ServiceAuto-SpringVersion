package com.gini.iordache.controllers.order;


import com.gini.iordache.controllers.HomeController;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.impl.order.LaborServiceOrderServiceImpl;
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


@Controller
@RequestMapping("/laborOrder")
public class LaborOrderController {

    private final LaborService laborService;
    private final HomeController homeController;
    private final LaborServiceOrderServiceImpl laborOrderService;
    private final ServiceOrderService serviceOrderService;

    List<Labor> labors = new ArrayList<>();
    List<LaborServiceOrder> orderLabors = new ArrayList<>();


    @Autowired
    public LaborOrderController(LaborService laborService, HomeController homeController, LaborServiceOrderServiceImpl laborOrderService, ServiceOrderService serviceOrderService) {
        this.laborService = laborService;
        this.homeController = homeController;
        this.laborOrderService = laborOrderService;
        this.serviceOrderService = serviceOrderService;
    }

    @GetMapping("/laborOrderPage")
    public String getLaborOrderPage(Model model){

        ServiceOrder serviceOrder = homeController.getServiceOrder(); //cad intru pe pagina ia timpii de manopera sii ii baga in tabel
        orderLabors = serviceOrderService.findAllLaborsInOrder(serviceOrder.getId());

        model.addAttribute("labors", labors);
        model.addAttribute("orderLabors",orderLabors);


        return "order/laborOrder-page";
    }


    @GetMapping("/searchLabor")
    public String searchLabor(HttpServletRequest request, Model model){



        var laborDescription = request.getParameter("laborDescription");

        labors.clear();                                                 // :-> resetam lista pentru urmatorul find

        labors = laborService.findLaborByName(laborDescription);
        model.addAttribute("labors", labors);
        model.addAttribute("orderLabors", orderLabors);
        System.out.println(labors);


        return "order/laborOrder-page";
    }


    @GetMapping("/addLaborToOrder")
    public String addLaborToOrder(HttpServletRequest request, Model model){


       ServiceOrder serviceOrder = homeController.getServiceOrder();

        int id = Integer.parseInt(request.getParameter("laborId"));

        labors.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .ifPresent(l -> laborOrderService.addLaborToServiceOrder(l,serviceOrder));


        orderLabors = serviceOrderService.findAllLaborsInOrder(serviceOrder.getId());

        model.addAttribute("orderLabors", orderLabors);
        model.addAttribute("labors", labors);

        return "order/laborOrder-page";
    }

    @PostMapping("/deleteLabor")
    public String deleteLaborOrder(HttpServletRequest request){

        var id = Integer.parseInt(request.getParameter("laborId"));

        laborOrderService.deleteLaborFromOrder(id);

        return "redirect:/laborOrder/laborOrderPage";
    }


}
