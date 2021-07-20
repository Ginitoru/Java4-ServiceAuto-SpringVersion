package com.gini.iordache.controllers.exceptionhadlers.lobor;

import com.gini.errors.LaborNotFoundException;
import com.gini.errors.labor.InvalidPriceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LaborExceptionHandler {

    @ExceptionHandler(InvalidPriceException.class)
    public String processInvalidPriceException(InvalidPriceException e){
        e.printStackTrace();

        return "redirect:/prices/showPrices?invalid";

    }


    @ExceptionHandler
    public String processLaborNotFoundException(LaborNotFoundException e){
        e.printStackTrace();

        return "redirect:/laborOrder/laborOrderPage?deleted";
    }
}
