package com.gini.iordache.controllers.exceptionhadlers.order;

import com.gini.errors.invoice.InvoiceException;
import com.gini.errors.order.*;
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
        return "redirect:/app/main";

    }

    @ExceptionHandler(PartNotFoundException.class)
    public String partNotFoundException(PartNotFoundException e){

        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?part";

    }

    @ExceptionHandler(NotEnoughPartsException.class)
    public String notEnoughPartsException(NotEnoughPartsException e){

        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?noPart";
    }

    @ExceptionHandler(BadIntegerNumberException.class)
    public String validIntegerNumberException(BadIntegerNumberException e){
        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?invalid";
    }

    @ExceptionHandler(SelectPartException.class)
    public String noPartWasSelectedException(SelectPartException e){
        e.printStackTrace();
        return "redirect:/orderPart/addPart-page?select";

    }

    @ExceptionHandler(SelectOrderException.class)
    public String processSelectOrderException(SelectOrderException e){
        e.printStackTrace();

        return "redirect:/app/main";
    }


    @ExceptionHandler(InvoiceException.class)
    public String processInvoiceException(InvoiceException e){
        e.printStackTrace();
        return "redirect:/app/main";

    }



}
