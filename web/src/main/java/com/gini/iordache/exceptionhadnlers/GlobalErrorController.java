package com.gini.iordache.exceptionhadnlers;



import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;


//prostesc aplicatia ca exceptiile sunt aruncate din acest contoller care este mapat la /error si nu din corul aplicatiei.
//iar cu clasele de @ControllerAdvice le rezolv. Astfel pot sa ma ocup de exceptiile ce se arunca la logare, creare cont autentificare etc.


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
