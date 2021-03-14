package com.gini.iordache.controllers.auto;

import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/vehicles")
public class VehicleController {


    private final VehicleService vehicleService;


    @GetMapping("/vehicle")
    public String getVehiclePage(Model model){

        model.addAttribute("vehicle", new Vehicle());

        return "app/vehicle-page";
    }


    @PostMapping("/create-vehicle")
    public String createVehicle(@ModelAttribute("vehicle") Vehicle vehicle){

        vehicleService.createVehicle(vehicle);

        return "redirect:/vehicles/vehicle";

    }



}
