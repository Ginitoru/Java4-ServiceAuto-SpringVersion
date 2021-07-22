package com.gini.iordache.exceptionhadlers.auto;


import com.gini.errors.auto.VehicleAlreadyExistsException;
import com.gini.errors.auto.VehicleNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VehicleExceptionHandlingController {


    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public String vehicleAlreadyExists(VehicleAlreadyExistsException e){
        e.printStackTrace();
        return "redirect:/vehicles/vehicle?exists";

    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public String vehicleNotFound(VehicleNotFoundException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder?vehicleNotFound";
    }

}
