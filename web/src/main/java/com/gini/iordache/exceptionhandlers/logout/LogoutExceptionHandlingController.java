package com.gini.iordache.exceptionhandlers.logout;


import com.gini.errors.logout.LogoutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LogoutExceptionHandlingController {


    @ExceptionHandler(LogoutException.class)
    public String processLogoutException(LogoutException e){
        e.printStackTrace();

        return "redirect:/logout3";
    }

}
