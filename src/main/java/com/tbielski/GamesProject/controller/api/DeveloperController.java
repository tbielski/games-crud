package com.tbielski.GamesProject.controller.api;

import com.tbielski.GamesProject.domain.entity.developer.Developer;
import com.tbielski.GamesProject.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(@Autowired DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/api/developers")
    List<Developer> getAllDevelopers() {
        return this.developerService.getAllDevelopers();
    }

    @GetMapping("/api/developers/{id}")
    Developer getDeveloperById(@PathVariable @NotNull Long id) {
        return this.developerService.getDeveloperById(id);
    }

    @PostMapping("/api/developers/add")
    void addNewDeveloper(@RequestBody @Valid Developer developer) {
        this.developerService.addNewDeveloper(developer);
    }

    @DeleteMapping("/api/developers/{id}")
    void deleteDeveloperById(@PathVariable @NotNull Long id) {
        this.developerService.deleteDeveloperById(id);
    }

    @PostMapping("/api/developers/edit")
    void editDeveloper(@RequestBody @Valid Developer developer) {
        this.developerService.editDeveloper(developer);
    }

}