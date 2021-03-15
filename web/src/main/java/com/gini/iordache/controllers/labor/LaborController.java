package com.gini.iordache.controllers.labor;

import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.services.LaborService;
import com.gini.iordache.utility.LaborCategory;
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


    private final LaborService laborService;


    @GetMapping("/labor")
    public String getLaborPage(Model model){

        model.addAttribute("labor", new Labor());
        model.addAttribute("laborCategory", LaborCategory.values());

        return "/labor/labor-page";
    }


    @PostMapping("/create-labor")
    public String createLabor(@ModelAttribute("labor") Labor labor){

        laborService.createLabor(labor);

        return "redirect:/labors/labor";
    }




}
