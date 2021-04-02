package com.gini.iordache.controllers.trim;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class TrimWhiteSpaceFromFields {  //face trim la white space pe toate fieldurile
                                         //am facut un @ControllerAdvice ca sa nu mai baga metoda asta in fiecare controller (AOP)
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
