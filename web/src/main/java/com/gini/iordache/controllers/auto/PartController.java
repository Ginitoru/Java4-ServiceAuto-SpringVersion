package com.gini.iordache.controllers.auto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parts")
public class PartController {


    @GetMapping("/part")
    public String getPartPage(){

        return "app/part-page";
    }


}
