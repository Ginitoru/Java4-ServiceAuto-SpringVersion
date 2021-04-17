package com.gini.iordache.controllers.order;


import com.gini.iordache.controllers.MiniCache;
import com.gini.iordache.entity.order.CarProblems;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.user.User;
import com.gini.iordache.services.interfaces.*;
import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/serviceOrder")
public class OrderController {


    private final OrderService serviceOrderService;
    private final UserService userService;
    private final MiniCache miniCache;


    @GetMapping("/serviceOrder")
    public String showServiceOrderPage(Model model){

        Optional.ofNullable(miniCache.getVehicle())
                    .ifPresentOrElse(vehicle -> model.addAttribute("vehicle", vehicle),
                                          () -> model.addAttribute("vehicle", miniCache.getEmptyVehicle()));

        Optional.ofNullable(miniCache.getPerson())
                    .ifPresentOrElse(person -> model.addAttribute("person", person),
                                         () -> model.addAttribute("person", miniCache.getEmptyPerson()));

        Optional.ofNullable(miniCache.getCompany())
                    .ifPresentOrElse(company -> model.addAttribute("company", company),
                                          () -> model.addAttribute("company", miniCache.getEmptyCompany()));

        return "order/serviceOrder-page";
    }


    @GetMapping("/searchCar")
    public String searchCarByVin(HttpServletRequest request, Model model){

        var serialNumber = request.getParameter("serialNumber");
        var vehicle = miniCache.findVehicleBySerialNumber(serialNumber);
        model.addAttribute("vehicle", vehicle);

        return "redirect:/serviceOrder/serviceOrder";
    }



    @GetMapping("/findPerson")
    public String searchPersonByCnp(HttpServletRequest request, Model model){

        var cnp = request.getParameter("cnp");
        var person = miniCache.findPersonByCnp(cnp);
        model.addAttribute("person", person);
        miniCache.resetCompanySearch();                           //->resetez company daca am dat search person

        return "redirect:/serviceOrder/serviceOrder";

    }

    @GetMapping("/findCompany")
    public String searchCompany(HttpServletRequest request, Model model){

        var cui = request.getParameter("cui");
        Company company = miniCache.findCompanyByCui(cui);
        model.addAttribute("company", company);
        miniCache.resetPersonSearch();                                      // -> resetez person daca am dat search company

        return "redirect:/serviceOrder/serviceOrder";
    }


    @PostMapping("/carProblems")    
    public String createServiceOrder(HttpServletRequest request){


        if(miniCache.getVehicle().getId() != 0 &&
                ((miniCache.getPerson().getId() != 0) || (miniCache.getCompany().getId() != 0))){


            var carProblems = request.getParameter("carProblems");

            String username = SecurityContextHolder
                                                .getContext()
                                                .getAuthentication()
                                                .getPrincipal()
                                                .toString();


            User user = userService.findUseByUsername(username);
            CarProblems problems = new CarProblems();
                        problems.setProblems(carProblems);


            ServiceOrder serviceOrder = new ServiceOrder.Builder()
                                                .withOrderStatus(OrderStatus.OPEN)
                                                .withCarProblems(problems)
                                                .withVehicle(miniCache.getVehicle())
                                                .withUser(user)
                                                .build();



            if(miniCache.getPerson().getId() == 0){
                serviceOrder.setClient(miniCache.getCompany());
            }

            if(miniCache.getCompany().getId() == 0){
                serviceOrder.setClient(miniCache.getPerson());
            }


            serviceOrderService.createServiceOrder(serviceOrder);

            miniCache.resetCarSearch();   //->reset valorile din search
            miniCache.resetPersonSearch();
            miniCache.resetCompanySearch();
        }


        return "redirect:/serviceOrder/serviceOrder";
    }
}
