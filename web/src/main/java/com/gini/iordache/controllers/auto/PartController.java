package com.gini.iordache.controllers.auto;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.PartCount;
import com.gini.iordache.services.PartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;


    @GetMapping("/part")
    public String getPartPage(Model model){

        model.addAttribute("part", new Part());
        model.addAttribute("partCount", new PartCount());


        return "app/part-page";
    }

    @PostMapping("/createPart")
    public String createPart(@ModelAttribute ("part") Part part){


        partService.addPart(part);

        return "redirect:/parts/part";
    }


}
