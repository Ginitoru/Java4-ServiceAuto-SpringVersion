package com.gini.iordache.controllers.exceptionhadlers;


import com.gini.errors.auto.VehicleNotFoundException;
import com.gini.errors.client.CompanyNotFoundException;
import com.gini.errors.client.PersonNotFoundException;
import com.gini.errors.user.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class ExceptionHandlingController {



    //todo: exception handler pt -> cand am user in baza de data si vreau sa creez unul la fel





    @ExceptionHandler(AccountAlreadyActiveException.class)
    public String processAccountAlreadyActive(AccountAlreadyActiveException e) {
        e.printStackTrace();
        return "redirect:/account?active";
    }


    @ExceptionHandler(TokenHasExpiredException.class)
    public String processTokenHasExpired(TokenHasExpiredException e) {
        e.printStackTrace();
        return "redirect:/token";
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public String userNotFound(UsernameNotFoundException e) {
        e.printStackTrace();
        return "redirect:/login?error";
    }


    @ExceptionHandler(BadCredentialsException.class)
    public String passwordNotValid(BadCredentialsException e) {
        e.printStackTrace();
        return "redirect:/login?error";
    }


    @ExceptionHandler(AccountIsNotActiveException.class)
    public String accountIsNotActive(AccountIsNotActiveException e){
        e.printStackTrace();
        return "redirect:/token";
    }

    @ExceptionHandler(EmailIsNotRegisteredException.class)
    public String emailIsNotRegistered(EmailIsNotRegisteredException e){
        e.printStackTrace();
        return "redirect:/token?error";
    }


    @ExceptionHandler(InvalidTokenException.class)
    public String invalidToken(InvalidTokenException e){
        e.printStackTrace();
        return "redirect:/account?invalidToken";
    }


    @ExceptionHandler(VehicleNotFoundException.class)
    public String vehicleNotFound(VehicleNotFoundException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder?vehicleNotFound";
    }


    @ExceptionHandler(PersonNotFoundException.class)
    public String personNotFound(PersonNotFoundException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder?personNotFound";
    }


    @ExceptionHandler(CompanyNotFoundException.class)
    public String companyNotFound(CompanyNotFoundException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder?companyNotFound";
    }









}
