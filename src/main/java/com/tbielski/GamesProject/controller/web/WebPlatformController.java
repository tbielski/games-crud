package com.tbielski.GamesProject.controller.web;

import com.tbielski.GamesProject.domain.entity.platform.Platform;
import com.tbielski.GamesProject.services.PlatformService;
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
public class WebPlatformController {
    private final PlatformService platformService;

    public WebPlatformController(@Autowired PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping("/platforms")
    public String allPlatforms(Model model) {
        model.addAttribute("allPlatforms", platformService.getAllPlatforms());
        return "platforms-all";
    }

    @GetMapping("/platformAddForm")
    public String platformAddForm(Model model) {
        model.addAttribute("platform", new Platform());
        return "platform-form";
    }

    @PostMapping("/platformAddForm")
    public String processPostOrderPlatform(@Valid Platform platform, Errors errors) {
        if (errors.hasErrors()) {
            return "platform-form";
        }
        platformService.addNewPlatform(platform);
        return "redirect:/platforms";
    }

    @GetMapping("/deletePlatform/{id}")
    public String processDeleteOrderPlatform(@PathVariable @NotNull Long id) {
        platformService.deletePlatformById(id);
        return "redirect:/platforms";
    }

    @GetMapping("/editPlatform/{id}")
    public String editPlatformForm(Model model, @PathVariable @NotNull Long id) {
        Platform platform = new Platform();
        platform.setId(id);
        model.addAttribute("platform", platform);
        return "platform-edit";
    }

    @PostMapping("/editPlatform")
    public String processEditOrderPlatform(@Valid Platform platform, Errors errors) {
        if (errors.hasErrors()) {
            return "platform-edit";
        }
        platformService.editPlatform(platform);
        return "redirect:/platforms";
    }

}
