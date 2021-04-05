package com.gini.iordache.controllers;

import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.InvoiceService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {



    private final ServiceOrderService serviceOrderService;
    private final InvoiceService invoiceService;

    private ServiceOrder serviceOrder = new ServiceOrder();


    @Autowired
    public HomeController(ServiceOrderService serviceOrderService, InvoiceService invoiceService) {
        this.serviceOrderService = serviceOrderService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/main")
    public String showHomePage(Model model){

        model.addAttribute("serviceOrderIdAndStatus", serviceOrderService.allServiceOrderIdAndStatus());
        allAllModelAtributes(model);


        return "main-page";
    }


    @GetMapping("/order-stats")  //method 1
    public String findOrderStats(@RequestParam("orderId") int id, Model model){

       serviceOrder = serviceOrderService.findCompleteServiceOrderById(id);
       allAllModelAtributes(model);

        return "redirect:/main";
    }


    @PostMapping("/closeOrder")
    public String closeOrder(){

        serviceOrderService.closeOrder(serviceOrder, serviceOrder.getTotalPrice(), serviceOrder.getTotalPriceVAT());

        return "redirect:/main";
    }


    @GetMapping("/invoice")
    public String getInvoice(){

        invoiceService.getInvoiceFromDataBase(serviceOrder);
        return "redirect:/main";
    }




    private void allAllModelAtributes(Model model){
        model.addAttribute("laborsOrder", serviceOrder.getLabors());
        model.addAttribute("partsOrder", serviceOrder.getParts());
        model.addAttribute("serviceOrder", serviceOrder);

        model.addAttribute("partsTotalPrice", serviceOrder.getPartsTotalPrice());
        model.addAttribute("partsTotalPriceWithVAT", serviceOrder.getPartsTotalPriceVAT());

        model.addAttribute("laborTotalPrice", serviceOrder.getLaborTotalPrice());
        model.addAttribute("laborTotalPriceWithVAT", serviceOrder.getLaborTotalPriceVAT());

        model.addAttribute("totalPrice", serviceOrder.getTotalPrice());
        model.addAttribute("totalPriceWithVAT", serviceOrder.getTotalPriceVAT());



    }





    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }
}
