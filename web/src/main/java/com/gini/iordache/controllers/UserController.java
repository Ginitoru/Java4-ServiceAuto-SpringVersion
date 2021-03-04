package com.gini.iordache.controllers;

import com.gini.iordache.entity.Authorities;
import com.gini.iordache.entity.User;
import com.gini.iordache.services.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("user", new User());
        return "user/login-user";
    }


    @PostMapping("/login-processing")
    public String loginProcessing(){
        return "redirect:/intra";
    }


    @GetMapping("/create-user")
    public String showCreateUserPage(Model model){
        model.addAttribute("newUser", new User());
        model.addAttribute("authority", Authorities.values());
        return "user/create-user";
    }


    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("newUser") User user){
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/activate")
    public String activateAccount(HttpServletRequest request, Model model){

        var token = request.getParameter("token");
        var username = request.getParameter("username");

        System.out.println( "token " + token + " username " + username);

        model.addAttribute("pacpac", token);
        model.addAttribute("theuser", username);

        userService.enableUserAccount(username, token);

        return "user/activate-user";
    }

//    @GetMapping("/activate{token}")
//    public String activateAccount(@RequestParam("token") String request, Model model){
//
//        model.addAttribute("pacpac", request);
//        System.out.println(request);
//
//
//
//         return "user/activate-user";
//    }
}
