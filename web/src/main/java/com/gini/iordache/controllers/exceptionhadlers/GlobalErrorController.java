package com.gini.iordache.controllers.exceptionhadlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerExceptionResolver;
import javax.servlet.http.HttpServletRequest;


//prostesc aplicatia ca exceptiile sunt aruncate din acest contoller care este mapat la /error si nu din corul aplicatiei.
//iar cu clasa de @ControllerAdvice le rezolv. Astfel pot sa ma ocup de exceptiile ce se arunca la logare, creere cont autentificare etc.


@Controller
public class GlobalErrorController implements ErrorController {


    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {

        if (request.getAttribute("javax.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
    }



    @Override
    public String getErrorPath() {
        return null;
    }
}
