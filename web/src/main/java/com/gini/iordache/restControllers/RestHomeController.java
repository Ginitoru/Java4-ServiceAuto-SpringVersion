package com.gini.iordache.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app2")
public class RestHomeController {

    @GetMapping("/hello")
    public String getName(){
        return " bau abu am intrat";
    }
}
