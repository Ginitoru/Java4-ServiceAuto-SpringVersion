package com.gini.iordache.controllers.auto;

import com.gini.iordache.controllers.HomeController;
import com.gini.iordache.dto.PartDto;
import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.PartService;
import com.gini.iordache.services.interfaces.PartServiceOrderService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
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

    private final PartServiceOrderService partServiceOrderService;
    private final ServiceOrderService serviceOrderService;
    private final HomeController homeController;
    private final PartService partService;

    private Part part = new Part();
    private List<Part> parts = new ArrayList<>();
    private List<Integer> partCount = new ArrayList<>();

    @Autowired
    public ServiceOrderPartController(PartServiceOrderService partServiceOrderService, ServiceOrderService serviceOrderService, HomeController homeController, PartService partService) {
        this.partServiceOrderService = partServiceOrderService;
        this.serviceOrderService = serviceOrderService;
        this.homeController = homeController;
        this.partService = partService;
    }


    //todo: selecturile sunt praf aici si mai face si Sesion in view aici ca nu reuseste sa ia lista de partCount din query
    //todo: need to rethink all this shit


    @GetMapping("/addPart-page")
    public String addPartsToServiceOrder(Model model){

        int id = homeController.getServiceOrder().getId();

        List<PartServiceOrder> partServiceOrders = serviceOrderService.getPartsFormServiceOrder(id);

        model.addAttribute("part", part);
        model.addAttribute("serviceOrderParts",partServiceOrders);


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
    public String addPartToOrder(HttpServletRequest request, Model model){

        var count = Integer.parseInt(request.getParameter("count"));
        ServiceOrder serviceOrder = homeController.getServiceOrder();

            //todo: de facut aici pretul
        partServiceOrderService.addPartToServiceOrder(part,serviceOrder,count, 20);


//        int id = homeController.getServiceOrder().getId();
//        PartDto partDto = serviceOrderService.getPartsFormServiceOrder(id);
//        List<PartServiceOrder> partServiceOrders = partDto.getPartServiceOrders();

        return "redirect:/addPart-page";
    }

}
