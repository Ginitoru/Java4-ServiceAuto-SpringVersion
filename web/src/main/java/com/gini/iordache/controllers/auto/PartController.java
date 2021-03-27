package com.gini.iordache.controllers.auto;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.PartCount;
import com.gini.iordache.services.interfaces.PartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;


    @GetMapping("/part")
    public String getPartPage(Model model){

        model.addAttribute("part", new Part());
        model.addAttribute("partCount", new PartCount());


        return "auto/part-page";
    }

    @PostMapping("/createPart")
    public String createPart(@Valid @ModelAttribute ("part") Part part, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
            return "auto/part-page";
        }


        partService.addPart(part);
        return "redirect:/parts/part";
    }


}
