package com.gini.iordache.controllers.auto;

import com.gini.iordache.controllers.HomeController;
import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.ServiceOrder;
import com.gini.iordache.services.PartService;
import com.gini.iordache.services.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ServiceOrderPartController {

    private final ServiceOrderService serviceOrderService;
    private final HomeController homeController;
    private final PartService partService;

    private Part part = new Part();
    private List<Part> parts = new ArrayList<>();
    private List<Integer> partCount = new ArrayList<>();


    @Autowired
    public ServiceOrderPartController(ServiceOrderService serviceOrderService, HomeController homeController, PartService partService) {
        this.serviceOrderService = serviceOrderService;
        this.homeController = homeController;
        this.partService = partService;
    }

    @GetMapping("/addPart-page")
    public String addPartsToServiceOrder(Model model){

        model.addAttribute("part", part);

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
    public String addPartToOrder(HttpServletRequest request){

        var count = Integer.parseInt(request.getParameter("count"));
        ServiceOrder serviceOrder = homeController.getServiceOrder();

        parts.add(part);
        partCount.add(count);
        serviceOrder.setParts(parts);
        serviceOrder.setPartCount(partCount);
        serviceOrderService.updateServiceOrder(serviceOrder);

        return "redirect:/addPart-page";
    }

}
