package com.gini.iordache.controllers.labor;

import com.gini.iordache.entity.labor.Labor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/labors")
public class LaborController {





    @GetMapping("/labor")
    public String getLaborPage(Model model){

        model.addAttribute("labor", new Labor());

        return "/labor/labor-page";
    }


    @PostMapping("/create-labor")
    public String createLabor(@ModelAttribute("labor") Labor labor){


        return "redirect:/labor";
    }




}
