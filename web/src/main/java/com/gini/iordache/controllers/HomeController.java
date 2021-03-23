package com.gini.iordache.controllers;



import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.PartService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import com.gini.iordache.services.impl.utility.AllOrdersIdAndStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {


    private final ServiceOrderService serviceOrderService;
    private final AllOrdersIdAndStatus allOrdersIdAndStatus;
    private final PartService partService;

    private ServiceOrder serviceOrder = new ServiceOrder();



    @Autowired
    public HomeController(ServiceOrderService serviceOrderService, AllOrdersIdAndStatus allOrdersIdAndStatus, PartService partService) {
        this.serviceOrderService = serviceOrderService;
        this.allOrdersIdAndStatus = allOrdersIdAndStatus;
        this.partService = partService;
    }




    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/main")
    public String intra(Model model){

        model.addAttribute("serviceOrderIdAndStatus", allOrdersIdAndStatus.getList());
        model.addAttribute("serviceOrder", serviceOrder);
        return "main-page";
    }


    @GetMapping("/order-stats")
    public String findOrderStats(@RequestParam("orderId") int id, Model model){


        //todo: cu 7 selecturi am luat tot ce e in serviceOrder :D ma asteptam la mai rau
       serviceOrder = serviceOrderService.findCompleteServiceOrderById(id);

        System.out.println(serviceOrder.getParts().toString());
        System.out.println(serviceOrder.getLabors().toString());

        model.addAttribute("serviceOrder", serviceOrder);


        return "redirect:/main";
    }


    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }
}
