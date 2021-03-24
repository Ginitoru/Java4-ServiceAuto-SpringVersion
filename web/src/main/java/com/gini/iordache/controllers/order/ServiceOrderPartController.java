package com.gini.iordache.controllers.order;

import com.gini.iordache.controllers.HomeController;
import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.PartService;
import com.gini.iordache.services.interfaces.PartServiceOrderService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ServiceOrderPartController {

    private final PartServiceOrderService partServiceOrderService;
    private final ServiceOrderService serviceOrderService;
    private final HomeController homeController;
    private final PartService partService;

    private Part part = new Part();


    @Autowired
    public ServiceOrderPartController(PartServiceOrderService partServiceOrderService, ServiceOrderService serviceOrderService, HomeController homeController, PartService partService) {
        this.partServiceOrderService = partServiceOrderService;
        this.serviceOrderService = serviceOrderService;
        this.homeController = homeController;
        this.partService = partService;
    }



    @GetMapping("/addPart-page")
    public String addPartsToServiceOrder(Model model){

        int id = homeController.getServiceOrder().getId();

        List<PartServiceOrder> partServiceOrders = serviceOrderService.getPartsFormServiceOrder(id);

        model.addAttribute("part", part);
        model.addAttribute("serviceOrderParts",partServiceOrders);


        return "auto/serviceOrderPart-page";
    }


    @GetMapping("/findPart")
    public String findPart(HttpServletRequest request, Model model){

        var partNumber = request.getParameter("partNumber");

        part = partService.findPartByPartNumber(partNumber);

        model.addAttribute("part", part);



        return "redirect:/addPart-page";
    }


    @PostMapping("/addPartToOrder")
    public String addPartToOrder(HttpServletRequest request, Model model){

        var count = Integer.parseInt(request.getParameter("count"));
        ServiceOrder serviceOrder = homeController.getServiceOrder();

        partServiceOrderService.addPartToServiceOrder(part,serviceOrder,count);
        return "redirect:/addPart-page";
    }


    @GetMapping("/deletePart")
    public String deletePartFromServiceOrder(@RequestParam("partNumber") String partNumber, @RequestParam("count") int count){


        partServiceOrderService.deletePartFromServiceOrder(partNumber, count);

        return "redirect:/addPart-page";
    }

}
