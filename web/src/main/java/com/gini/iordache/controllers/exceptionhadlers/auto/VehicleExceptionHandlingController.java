package com.gini.iordache.controllers.exceptionhadlers.auto;


import com.gini.errors.auto.VehicleAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VehicleExceptionHandlingController {


    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public String vehicleAlreadyExists(VehicleAlreadyExistsException e){
        e.printStackTrace();
        return "redirect:/vehicles/vehicle?exists";

    }

}
