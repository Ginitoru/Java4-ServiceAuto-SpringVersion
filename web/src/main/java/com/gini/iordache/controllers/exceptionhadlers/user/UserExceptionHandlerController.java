package com.gini.iordache.controllers.exceptionhadlers.user;

import com.gini.errors.user.UserAlreadyExists;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandlerController {

    @ExceptionHandler(UserAlreadyExists.class)
    public String catchUserAlreadyExistException(UserAlreadyExists e){

        e.printStackTrace();

        return "redirect:/create-user?exists";
    }



}
