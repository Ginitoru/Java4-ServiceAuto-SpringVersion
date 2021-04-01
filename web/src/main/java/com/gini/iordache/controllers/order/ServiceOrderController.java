package com.gini.iordache.controllers.order;


import com.gini.iordache.entity.order.CarProblems;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.entity.user.User;
import com.gini.iordache.services.interfaces.*;
import com.gini.iordache.utility.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/serviceOrder")
public class ServiceOrderController {


    private final VehicleService vehicleService;
    private final PersonService personService;
    private final CompanyService companyService;
    private final ServiceOrderService serviceOrderService;
    private final UserService userService;


    private Vehicle vehicle = new Vehicle();
    private Person person = new Person();
    private Company company = new Company();



    @Autowired
    public ServiceOrderController(VehicleService vehicleService,
                                        PersonService personService,
                                             CompanyService companyService,
                                                         UserService userService,
                                                                ServiceOrderService serviceOrderService) {

        this.vehicleService = vehicleService;
        this.personService = personService;
        this.companyService = companyService;
        this.serviceOrderService = serviceOrderService;
        this.userService = userService;
    }








    @GetMapping("/serviceOrder")
    public String showServiceOrderPage(Model model){

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("person", person);
        model.addAttribute("company", company);

        return "auto/serviceOrder-page";
    }


    @GetMapping("/searchCar")
    public String searchCarByVin(HttpServletRequest request, Model model){

        var serialNumber = request.getParameter("serialNumber");
        vehicle = vehicleService.findVehicleBySerialNumber(serialNumber);
        model.addAttribute("vehicle", vehicle);

        return "redirect:/serviceOrder/serviceOrder";
    }



    @GetMapping("/findPerson")
    public String searchPersonByCnp(HttpServletRequest request, Model model){

        var cnp = request.getParameter("cnp");
        person = personService.findPersonByCnp(cnp);
        model.addAttribute("person", person);
        company = new Company();                            //->resetez company daca am dat search

        return "redirect:/serviceOrder/serviceOrder";

    }

    @GetMapping("/findCompany")
    public String searchCompany(HttpServletRequest request, Model model){

        var cui = request.getParameter("cui");
        company = companyService.findCompanyByCui(cui);
        model.addAttribute("company", company);
        person = new Person();                                      // -> resetez person daca am dat search

        return "redirect:/serviceOrder/serviceOrder";
    }


    @PostMapping("/carProblems")    
    public String createServiceOrder(HttpServletRequest request){

        var carProblems = request.getParameter("carProblems");


        CarProblems problems = new CarProblems();
        problems.setProblems(carProblems);


        String username = SecurityContextHolder
                                        .getContext()
                                                .getAuthentication()
                                                        .getPrincipal()
                                                                .toString();


                                 User user = userService.findUseByUsername(username);


        ServiceOrder serviceOrder = new ServiceOrder.Builder()
                        .withOrderStatus(OrderStatus.OPEN)
                    .withCarProblems(problems)
                .withVehicle(vehicle)
            .withUser(user)
        .build();



        if(person.getId() == 0){
            serviceOrder.setClient(company);
        }

        if(company.getId() == 0){
            serviceOrder.setClient(person);
        }


        serviceOrderService.createServiceOrder(serviceOrder);
        vehicle = new Vehicle();   //->reset valorile din search
        person = new Person();
        company = new Company();

        return "redirect:/serviceOrder/serviceOrder";
    }

}
