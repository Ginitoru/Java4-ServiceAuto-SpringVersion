package com.gini.iordache.controllers.exceptionhadlers;


import com.gini.errors.AccountAlreadyActive;
import com.gini.errors.TokenHasExpired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class ExceptionHandlingController {


    @ExceptionHandler(AccountAlreadyActive.class)
    public String processAccountAlreadyActive(AccountAlreadyActive e) {

        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        return "redirect:/account-active";
    }

    @ExceptionHandler(TokenHasExpired.class)
    public String processTokenHasExpired(TokenHasExpired e) {

        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        return "redirect:/token";
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public String userNotFound(UsernameNotFoundException e) {

        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        return "redirect:/login?error";
    }


    @ExceptionHandler(BadCredentialsException.class)
    public String passwordNotValid(BadCredentialsException e) {

        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        return "redirect:/login?error";
    }







}
