package com.tbielski.GamesProject.controller.api;

import com.tbielski.GamesProject.domain.entity.platform.Platform;
import com.tbielski.GamesProject.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(@Autowired PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping("/api/platforms")
    List<Platform> getAllPlatforms() {
        return this.platformService.getAllPlatforms();
    }

    @GetMapping("/api/platforms/{id}")
    Platform getPlatformById(@PathVariable @NotNull Long id) {
        return this.platformService.getPlatformById(id);
    }

    @PostMapping("/api/platforms/add")
    void addNewPlatform(@RequestBody @Valid Platform platform) {
        this.platformService.addNewPlatform(platform);
    }

    @DeleteMapping("/api/platforms/{id}")
    void deletePlatformById(@PathVariable @NotNull Long id) {
        this.platformService.deletePlatformById(id);
    }

    @PostMapping("/api/platforms/edit")
    void editPlatform(@RequestBody @Valid Platform platform) {
        this.platformService.editPlatform(platform);
    }

}