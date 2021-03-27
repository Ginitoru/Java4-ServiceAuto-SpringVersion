package com.gini.iordache.controllers.exceptionhadlers.client;

import com.gini.errors.client.CompanyAlreadyExistsException;
import com.gini.errors.client.PersonAlreadyExistsException;
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


}
