package com.gini.iordache.controllers.home;

import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.InvoiceService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/app")
public class HomeController {



    private final ServiceOrderService serviceOrderService;
    private final InvoiceService invoiceService;
    private ServiceOrder serviceOrder = new ServiceOrder();


    @Autowired
    public HomeController(ServiceOrderService serviceOrderService, InvoiceService invoiceService) {
        this.serviceOrderService = serviceOrderService;
        this.invoiceService = invoiceService;
    }



    @GetMapping("/main")
    public String showHomePage(Model model){

        model.addAttribute("serviceOrderIdAndStatus", serviceOrderService.allServiceOrderIdAndStatus());
        allAllModelAtributes(model);


        return "home/home-page";
    }


    @GetMapping("/order-stats")  //method 1
    public String findOrderStats(@RequestParam("orderId") int id, Model model){

       serviceOrder = serviceOrderService.findCompleteServiceOrderById(id);
       allAllModelAtributes(model);

        return "redirect:/app/main";
    }


    @PostMapping("/closeOrder")
    public String closeOrder(){

        serviceOrderService.closeOrder(serviceOrder);

        return "redirect:/app/main";
    }


    @GetMapping("/invoice")
    public String getInvoice(){

        invoiceService.getInvoiceFromDataBase(serviceOrder);
        return "redirect:/app/main";
    }




    private void allAllModelAtributes(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("laborsOrder", serviceOrder.getLabors());
        model.addAttribute("partsOrder", serviceOrder.getParts());
        model.addAttribute("serviceOrder", serviceOrder);

        model.addAttribute("partsTotalPrice", serviceOrder.getPartsTotalPrice());
        model.addAttribute("partsTotalPriceWithVAT", serviceOrder.getPartsTotalPriceVAT());

        model.addAttribute("laborTotalPrice", serviceOrder.getLaborTotalPrice());
        model.addAttribute("laborTotalPriceWithVAT", serviceOrder.getLaborTotalPriceVAT());

        model.addAttribute("totalPrice", serviceOrder.getTotalPrice());
        model.addAttribute("totalPriceWithVAT", serviceOrder.getTotalPriceVAT());

        model.addAttribute("username", username);



    }





    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }
}
