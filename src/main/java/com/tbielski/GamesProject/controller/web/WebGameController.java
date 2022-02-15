package com.tbielski.GamesProject.controller.web;

import com.tbielski.GamesProject.domain.dto.game.DtoAddGame;
import com.tbielski.GamesProject.domain.dto.game.DtoAddPlatform;
import com.tbielski.GamesProject.domain.dto.game.DtoEditGame;
import com.tbielski.GamesProject.domain.entity.game.Game;
import com.tbielski.GamesProject.services.GameService;
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
public class WebGameController {
    private final GameService gameService;

    public WebGameController(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String allGames(Model model) {
        model.addAttribute("allGames", gameService.getAllGamesDto());
        return "games-all";
    }

    @GetMapping("/gameAddForm")
    public String gameAddForm(Model model) {
        model.addAttribute("dtoAddGame", new DtoAddGame());
        return "game-form";
    }

    @PostMapping("/gameAddForm")
    public String processPostOrderGame(@Valid DtoAddGame dtoAddGame, Errors errors) {
        if (errors.hasErrors()) {
            return "game-form";
        }
        Game gameToAdd = gameService.gameFromDto(dtoAddGame);
        this.gameService.addNewGame(gameToAdd);
        return "redirect:/games";
    }

    @GetMapping("/deleteGame/{id}")
    public String processDeleteOrderGame(@PathVariable @NotNull Long id) {
        gameService.deleteGameById(id);
        return "redirect:/games";
    }

    @GetMapping("/editGame/{id}")
    public String editGameForm(Model model, @PathVariable @NotNull Long id) {
        DtoEditGame dtoGame = new DtoEditGame();
        dtoGame.setId(id);
        model.addAttribute("dtoEditGame", dtoGame);
        return "game-edit";
    }

    @PostMapping("/editGame")
    public String processEditOrderGame(@Valid DtoEditGame dtoEditGame, Errors errors) {
        if (errors.hasErrors()) {
            return "game-edit";
        }
        gameService.editGame(dtoEditGame);
        return "redirect:/games";
    }

    @GetMapping("/addPlatform/{id}")
    public String addPlatformGameForm(Model model, @PathVariable @NotNull Long id) {
        DtoAddPlatform dtoAddPlatform = new DtoAddPlatform();
        dtoAddPlatform.setId(id);
        model.addAttribute("addPlatformToGame", dtoAddPlatform);
        return "game-add-platform";
    }

    @PostMapping("/addPlatform")
    public String processAddPlatformOrderGame(@Valid DtoAddPlatform dtoAddPlatform, Errors errors) {
        if (errors.hasErrors()) {
            return "game-add-platform";
        }
        gameService.addPlatform(dtoAddPlatform);
        return "redirect:/games";
    }

    @GetMapping("/deletePlatform/{idGame}/{idPlatform}")
    public String processDeleteOrderPlatformGame(@PathVariable @NotNull Long idGame, @PathVariable @NotNull Long idPlatform) {
        gameService.deletePlatformGameById(idGame, idPlatform);
        return "redirect:/games";
    }

}
