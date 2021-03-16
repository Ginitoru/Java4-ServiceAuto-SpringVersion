package com.gini.iordache.controllers.labor;

import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.services.LaborService;
import com.gini.iordache.utility.LaborCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

        //todo: de vazut cum facem aici ca face select de fiecare data cand incarca pagina
    @GetMapping("/laborsList")
    public String getLabors(HttpServletRequest request, Model model){



        if(request.getParameter("laborId") == null){


        }else{
            var id = Integer.parseInt(request.getParameter("laborId"));

            var labor = laborService.findLaborById(id);

            model.addAttribute("laborUpdate", labor);
        }



        model.addAttribute("labor", new Labor());
        model.addAttribute("laborCategory", LaborCategory.values());
        model.addAttribute("laborList", laborService.findAllLabors());



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
