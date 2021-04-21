package com.gini.iordache.controllers.labor;

import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.services.interfaces.LaborService;
import com.gini.iordache.utility.LaborCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/labors")
public class LaborController {


    private final LaborService laborService;

    private List<Labor> labors = new ArrayList<>();


    @Autowired
    public LaborController(LaborService laborService) {
        this.laborService = laborService;
    }

    @GetMapping("/labor")
    public String getLaborPage(Model model){

        model.addAttribute("labor", new Labor());
        model.addAttribute("laborCategory", LaborCategory.values());
        model.addAttribute("laborList", labors);
        model.addAttribute("laborUpdate", new Labor());

        return "/labor/labor-page";
    }


    @PostMapping("/create-labor")
    public String createLabor(@Valid @ModelAttribute("labor") Labor labor, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("laborCategory", LaborCategory.values());
            return "/labor/labor-page";
        }

        laborService.createLabor(labor);
        return "redirect:/labors/labor";
    }


    @GetMapping("/laborsList")
    public String getLabors(HttpServletRequest request, Model model){


        if(request.getParameter("laborDescription") == null ||
                request.getParameter("laborDescription").equals("")){

                 labors = laborService.findAllLabors();
                 model.addAttribute("laborList", labors);

        }else{

                 var laborDescription = request.getParameter("laborDescription");
                 labors = laborService.findLaborByName(laborDescription);
                 model.addAttribute("laborList", labors);
        }



        if(request.getParameter("laborId") != null){

            var laborId = Integer.parseInt(request.getParameter("laborId"));
            Labor labor = laborService.findLaborById(laborId);
            model.addAttribute("laborUpdate", labor);
        }

        model.addAttribute("labor", new Labor());
        model.addAttribute("laborCategory", LaborCategory.values());

      return "/labor/labor-page";

    }


    @PostMapping("/update")
    public String updateLabor(@ModelAttribute("laborUpdate") Labor labor){

        laborService.updateLaborTimeAndDescription(labor.getTimedLabor(),
                                                   labor.getLaborDescription(),
                                                   labor.getId());

        return "redirect:/labors/laborsList";
    }


    @GetMapping("/delete")
    public String deleteLabor(@RequestParam("laborId") int id){

          laborService.deleteLabor(id);

        return "redirect:/labors/laborsList";
    }







}
