package com.gini.iordache.controllers.exceptionhadlers;


import com.gini.errors.AccountAlreadyActive;
import com.gini.errors.AccountIsNotActive;
import com.gini.errors.TokenHasExpired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class ExceptionHandlingController {


    @ExceptionHandler(AccountAlreadyActive.class)
    public String processAccountAlreadyActive(AccountAlreadyActive e) {

        return "redirect:/account?active";
    }

    @ExceptionHandler(TokenHasExpired.class)
    public String processTokenHasExpired(TokenHasExpired e) {

        return "redirect:/token";
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public String userNotFound(UsernameNotFoundException e) {

        return "redirect:/login?error";
    }


    @ExceptionHandler(BadCredentialsException.class)
    public String passwordNotValid(BadCredentialsException e) {

        return "redirect:/login?error";
    }

    @ExceptionHandler(AccountIsNotActive.class)
    public String accountIsNotActive(AccountIsNotActive e){
        return "redirect:/token";
    }







}
