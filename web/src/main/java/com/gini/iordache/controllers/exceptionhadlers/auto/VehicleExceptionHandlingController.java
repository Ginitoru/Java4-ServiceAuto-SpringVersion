package com.gini.iordache.controllers.exceptionhadlers.auto;


import com.gini.errors.auto.VehicleAlreadyExists;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VehicleExceptionHandlingController {


    @ExceptionHandler
    public String vehicleAlreadyExists(VehicleAlreadyExists e){
        return "redirect:/vehicles/vehicle?exists";

    }
}
