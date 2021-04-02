package com.gini.iordache.controllers.exceptionhadlers.order;

import com.gini.errors.order.ClientNotSelectedException;
import com.gini.errors.order.OrderIsClosedException;
import com.gini.errors.order.VehicleNotSelectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(VehicleNotSelectedException.class)
    public String vehicleNotSelectedException(VehicleNotSelectedException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder";

    }

    @ExceptionHandler(ClientNotSelectedException.class)
    public String clientNotFoundException(ClientNotSelectedException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder";
    }

    @ExceptionHandler(OrderIsClosedException.class)
    public String orderIsClosedException(OrderIsClosedException e){

        e.printStackTrace();
        return "redirect:/main";

    }



}
