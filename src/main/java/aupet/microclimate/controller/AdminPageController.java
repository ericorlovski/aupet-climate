package aupet.microclimate.controller;

import aupet.microclimate.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/climate")
@RequiredArgsConstructor
public class AdminPageController {

    private final IAdminService adminService;

    @GetMapping(value = "/")
    public ModelAndView getAdminPage(Model model) {
        model.addAttribute("climate", adminService.getAllHunTempActive());
        model.addAttribute("formErrors", 0);

        return new ModelAndView("climate","model", model);
    }

    @GetMapping(value = "/toggle_climate/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ModelAndView toggleCategoryActivity(@PathVariable Long id) {
        adminService.toggleClimateActivity(id);
        return new ModelAndView("redirect:/climate/");
    }
}