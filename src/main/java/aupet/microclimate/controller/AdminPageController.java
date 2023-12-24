package aupet.microclimate.controller;

import aupet.microclimate.model.repository.HumTempRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/climate")
@RequiredArgsConstructor
public class AdminPageController {

    private final HumTempRepository climateRepository;
    @GetMapping(value = "/")
    public ModelAndView getAdminPage(Model model) {
        val entity = climateRepository.getAllClimateList();

        model.addAttribute("climate", entity);
        model.addAttribute("formErrors", 0);

        return new ModelAndView("climate","model", model);
    }

}