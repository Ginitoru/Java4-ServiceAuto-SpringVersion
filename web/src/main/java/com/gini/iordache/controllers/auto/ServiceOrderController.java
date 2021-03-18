package com.gini.iordache.controllers.auto;

import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.services.CompanyService;
import com.gini.iordache.services.PersonService;
import com.gini.iordache.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/serviceOrder")
public class ServiceOrderController {


    private final VehicleService vehicleService;
    private final PersonService personService;
    private final CompanyService companyService;


    private Vehicle vehicle = new Vehicle();
    private Person person = new Person();
    private Company company = new Company();


    @Autowired
    public ServiceOrderController(VehicleService vehicleService, PersonService personService, CompanyService companyService) {
        this.vehicleService = vehicleService;
        this.personService = personService;
        this.companyService = companyService;
    }



    @GetMapping("/serviceOrder")
    public String showServiceOrderPage(Model model){

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("person", person);
        model.addAttribute("company", company);

        System.out.println(vehicle);
        System.out.println(person);


        return "auto/serviceOrder-page";
    }


    @GetMapping("/searchCar")
    public String searchCarByVin(HttpServletRequest request, Model model){

        var serialNumber = request.getParameter("serialNumber");
        vehicle = vehicleService.findVehicleBySerialNumber(serialNumber);
        model.addAttribute("vehicle", vehicle);

        return "redirect:/serviceOrder/serviceOrder";
    }


    @GetMapping("/searchPerson")
    public String searchPersonByCnp(HttpServletRequest request, Model model){

        var cnp = request.getParameter("cnp");
        person = personService.findPersonByCnp(cnp);
        model.addAttribute("person", person);

        return "redirect:/serviceOrder/serviceOrder";

    }

}
