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


    @GetMapping("/laborsList")
    public String getLabors(HttpServletRequest request, Model model){


        if(request.getParameter("laborDescription") == null ||
                request.getParameter("laborDescription").equals("")

        ){

                 model.addAttribute("laborList", laborService.findAllLabors());

        }else{

                 var laborDescription = request.getParameter("laborDescription");

                 var labor = laborService.findLaborByName(laborDescription);

                 model.addAttribute("laborList", labor);

        }



        if(request.getParameter("laborId") == null){

        }else{

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
