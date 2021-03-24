package com.gini.iordache.controllers;



import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.PartService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import com.gini.iordache.services.impl.utility.AllOrdersIdAndStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@AllArgsConstructor
@Controller
public class HomeController {


    private final ServiceOrderService serviceOrderService;


    private ServiceOrder serviceOrder = new ServiceOrder();
    private double partsTotalPrice;
    private double partsTotalPriceWithVAT;
    private double laborTotalPrice;
    private double laborTotalPriceWithVAT;
    private double totalPrice;
    private double totalPriceWithVAT;


    @Autowired
    public HomeController(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;

    }




    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/main")
    public String intra(Model model){

        model.addAttribute("laborsOrder", serviceOrder.getLabors());
        model.addAttribute("partsOrder", serviceOrder.getParts());
        model.addAttribute("serviceOrderIdAndStatus", serviceOrderService.allServiceOrderIdAndStatus());
        model.addAttribute("serviceOrder", serviceOrder);
        model.addAttribute("partsTotalPrice", partsTotalPrice);
        model.addAttribute("partsTotalPriceWithVAT", partsTotalPriceWithVAT);
        model.addAttribute("laborTotalPrice", laborTotalPrice);
        model.addAttribute("laborTotalPriceWithVAT", laborTotalPriceWithVAT);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalPriceWithVAT", totalPriceWithVAT);

        return "main-page";
    }

            //todo: cam dubioasa metoda asta -> de vezut daca pot sa ii fac ceva la sfarsit
    @GetMapping("/order-stats")  //method 1
    public String findOrderStats(@RequestParam("orderId") int id, Model model){


        //todo: cu 7 selecturi am luat tot ce e in serviceOrder :D ma asteptam la mai rau
       serviceOrder = serviceOrderService.findCompleteServiceOrderById(id);

        partsTotalPrice = getPartsTotalPrice(serviceOrder);
        partsTotalPriceWithVAT = partsTotalPrice * 1.19;

        laborTotalPrice = getLaborTotalPrice(serviceOrder);
        laborTotalPriceWithVAT =laborTotalPrice * 1.19;

        totalPrice = getTotalPriceRounded(partsTotalPriceWithVAT, laborTotalPriceWithVAT);
        totalPriceWithVAT  = getTotalPriceRounded(totalPrice);



        System.out.println(serviceOrder.getParts().toString());
        System.out.println(serviceOrder.getLabors().toString());
        System.out.println(serviceOrder);

        model.addAttribute("serviceOrder", serviceOrder);
        model.addAttribute("laborsOrder", serviceOrder.getLabors());
        model.addAttribute("partsOrder", serviceOrder.getParts());
        model.addAttribute("partsTotalPrice", partsTotalPrice);
        model.addAttribute("partsTotalPriceWithVAT", partsTotalPriceWithVAT);
        model.addAttribute("laborTotalPrice", laborTotalPrice);
        model.addAttribute("laborTotalPriceWithVAT", laborTotalPriceWithVAT);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalPriceWithVAT", totalPriceWithVAT);

        return "redirect:/main";
    }



    //method 2
    private double getPartsTotalPrice(ServiceOrder serviceOrder){
        return serviceOrder.getParts()
                                .stream()
                                .mapToDouble(this::partPrice)
                                .sum();                               //face suma preturilor la piese

    }


    //method 3
    private double partPrice(PartServiceOrder part){
        return part.getCount() * part.getPrice();
    }


    //method 4
    private double getLaborTotalPrice(ServiceOrder serviceOrder){

        return serviceOrder.getLabors()
                            .stream()
                            .mapToDouble(LaborServiceOrder::getLaborPrice)
                            .sum();

    }


    private double getTotalPriceRounded(double partPrice, double laborPrice){
        DecimalFormat format = new DecimalFormat("0.00");

        double totalPrice = partPrice + laborPrice;


        return Double.parseDouble(format.format(totalPrice));
    }


    private double getTotalPriceRounded(double totalPriceWithVAT){
        DecimalFormat format = new DecimalFormat("0.00");

        double totalPrice = totalPriceWithVAT * 1.19;

        return Double.parseDouble(format.format(totalPrice));
    }



    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }
}
