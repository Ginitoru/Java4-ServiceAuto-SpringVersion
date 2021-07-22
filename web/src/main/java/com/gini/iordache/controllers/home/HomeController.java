package com.gini.iordache.controllers.home;

import com.gini.errors.order.SelectOrderException;
import com.gini.iordache.cache.MiniCache;

import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.InvoiceService;
import com.gini.iordache.services.interfaces.OrderService;

import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Controller
@RequestMapping("/app")
public class HomeController {

    private final OrderService orderService;
    private final InvoiceService invoiceService;
    private final MiniCache miniCache;


    @GetMapping("/main")
    public String showHomePage(Model model){

        if(miniCache.getCompleteServiceOrder() == null){
            allModelAtributes(new ServiceOrder(), model);
        }else{

            allModelAtributes(miniCache.getCompleteServiceOrder(), model);
        }

        model.addAttribute("serviceOrderIdAndStatus", orderService.allServiceOrderIdAndStatus());

        return "home/home-page";
    }


    @GetMapping("/order-stats")  //method 1
    public String findOrderStats(@RequestParam("orderId") int id, Model model){

        ServiceOrder serviceOrder = miniCache.loadCompleteServiceOrderById(id);
        allModelAtributes(serviceOrder,model);

        return "redirect:/app/main";
    }


    @PostMapping("/closeOrder")
    public String closeOrder(){

        var username = SecurityContextHolder.getContext().getAuthentication().getName();


        if(miniCache.getCompleteServiceOrder() == null){
            throw  new SelectOrderException("No order selected");
        }


        orderService.closeOrder(miniCache.getCompleteServiceOrder());
        miniCache.getCompleteServiceOrder().setOrderStatus(OrderStatus.CLOSE);  //todo: de sters asta cand implemetez cu cache
        return "redirect:/app/main";                                            //todo: momentan nu pot sa o bag in service pe tranzactie deoarece creez
    }                                                                           //todo: circular dependency


    @GetMapping("/invoice") //https://stackoverflow.com/questions/21039471/spring-getoutputstream-has-already-been-called-for-this-response
    public String getInvoice(HttpServletResponse response,  Model model){

        if(miniCache.getCompleteServiceOrder() == null){
            throw  new SelectOrderException("No order selected");
        }

        invoiceService.getInvoiceFromDataBase(miniCache.getCompleteServiceOrder(), response);


        model.addAttribute("serviceOrderIdAndStatus", orderService.allServiceOrderIdAndStatus());
        return null; // bug fix -> returnez null in loc de view ca sa pot dll factura:DDDDD ca altfel o ia razna
    }




    private void allModelAtributes(ServiceOrder serviceOrder, Model model){
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

}
