package com.gini.iordache.controllers;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class ExceptionHandlingController {


    @ExceptionHandler(IllegalArgumentException.class)
    public String processRuntimeException(IllegalArgumentException e) {

        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        return "username";
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
