package com.gini.iordache.controllers.order;

import com.gini.errors.order.BadIntegerNumberException;
import com.gini.errors.order.PartNotFoundException;
import com.gini.errors.order.SelectPartException;
import com.gini.iordache.controllers.home.HomeController;
import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.PartService;
import com.gini.iordache.services.interfaces.PartServiceOrderService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/orderPart")
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

        List<PartOrder> partServiceOrders = serviceOrderService.getPartsFormServiceOrder(id);

        model.addAttribute("part", part);
        model.addAttribute("serviceOrderParts",partServiceOrders);


        return "order/partOrder-page";
    }


    @GetMapping("/findPart")
    public String findPart(HttpServletRequest request, Model model){

        var partNumber = request.getParameter("partNumber");


        try{
            part = partService.findPartByPartNumber(partNumber); //prind si rearunc exceptia ca sa resetez instanta de 'part'
        }catch(PartNotFoundException e){                         //ce face sa se goleasca field-urile din partOrder.html si
        e.printStackTrace();                                     //sa imi apara si 'part not found'
            part = new Part();
            throw new PartNotFoundException();
        }


        model.addAttribute("part", part);
        return "redirect:/orderPart/addPart-page";
    }


    @PostMapping("/addPartToOrder")
    public String addPartToOrder(HttpServletRequest request){

        if(part.getId() == 0){
            throw new SelectPartException("No part was selected");
        }

        try{

            var count = Integer.parseInt(request.getParameter("count"));
            ServiceOrder serviceOrder = homeController.getServiceOrder();
            partServiceOrderService.addPartToServiceOrder(part,serviceOrder,count);

        }catch(NumberFormatException e){
            e.printStackTrace();
            throw new BadIntegerNumberException("The value is not an integer");

        }


        return "redirect:/orderPart/addPart-page";
    }


    @GetMapping("/deletePart")
    public String deletePartFromServiceOrder(@RequestParam("partNumber") String partNumber, @RequestParam("count") int count){


        partServiceOrderService.deletePartFromServiceOrder(partNumber, count);

        return "redirect:/orderPart/addPart-page";
    }

}
