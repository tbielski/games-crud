package com.tbielski.GamesProject.controller.web;

import com.tbielski.GamesProject.domain.entity.developer.Developer;
import com.tbielski.GamesProject.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class WebDeveloperController {
    private final DeveloperService developerService;

    public WebDeveloperController(@Autowired DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/developers")
    public String allDevelopers(Model model) {
        model.addAttribute("allDevelopers", developerService.getAllDevelopers());
        return "developers-all";
    }

    @GetMapping("/developerAddForm")
    public String developerAddForm(Model model) {
        model.addAttribute("developer", new Developer());
        return "developer-form";
    }

    @PostMapping("/developerAddForm")
    public String processPostOrderDeveloper(@Valid Developer developer, Errors errors) {
        if (errors.hasErrors()) {
            return "developer-form";
        }
        developerService.addNewDeveloper(developer);
        return "redirect:/developers";
    }

    @GetMapping("/deleteDeveloper/{id}")
    public String processDeleteOrderDeveloper(@PathVariable @NotNull Long id) {
        developerService.deleteDeveloperById(id);
        return "redirect:/developers";
    }

    @GetMapping("/editDeveloper/{id}")
    public String editDeveloperForm(Model model, @PathVariable @NotNull Long id) {
        Developer developer = new Developer();
        developer.setId(id);
        model.addAttribute("developer", developer);
        return "developer-edit";
    }

    @PostMapping("/editDeveloper")
    public String processEditOrderDeveloper(@Valid Developer developer, Errors errors) {
        if (errors.hasErrors()) {
            return "developer-edit";
        }
        developerService.editDeveloper(developer);
        return "redirect:/developers";
    }

}
