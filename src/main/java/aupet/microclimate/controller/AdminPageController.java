package aupet.microclimate.controller;

import aupet.microclimate.model.repository.HumTempRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
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

    private final HumTempRepository humTempRepository;
    @GetMapping(value = "/")
    public ModelAndView getAdminPage(Model model) {
        val entity = humTempRepository.findAll();

        model.addAttribute("climate", entity);
        model.addAttribute("formErrors", 0);

        return new ModelAndView("climate","model", model);
    }

    @GetMapping(value = "/toggle_climate/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ModelAndView toggleCategoryActivity(@PathVariable Long id) {
        val cat = humTempRepository.findById(id);
        cat.ifPresent(food -> food.setActive(!food.isActive()));
        return new ModelAndView("redirect:/climate/");
    }
}