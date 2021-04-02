package com.gini.iordache.controllers.exceptionhadlers.client;

import com.gini.errors.client.CompanyAlreadyExistsException;
import com.gini.errors.client.CompanyNotFoundException;
import com.gini.errors.client.PersonAlreadyExistsException;
import com.gini.errors.client.PersonNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandlerController {

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public String personAlreadyExistException(PersonAlreadyExistsException e){
        e.printStackTrace();
        return "redirect:/clients/personForm?exists";
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public String companyAlreadyExists(CompanyAlreadyExistsException e){
        e.printStackTrace();
        return "redirect:/clients/companyForm?exists";
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public String companyNotFound(CompanyNotFoundException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder?companyNotFound";
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public String personNotFound(PersonNotFoundException e){
        e.printStackTrace();
        return "redirect:/serviceOrder/serviceOrder?personNotFound";
    }


}
