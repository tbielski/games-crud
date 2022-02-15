package com.tbielski.GamesProject.controller.api;

import com.tbielski.GamesProject.domain.dto.game.DtoAddGame;
import com.tbielski.GamesProject.domain.dto.game.DtoEditGame;
import com.tbielski.GamesProject.domain.dto.game.DtoGameView;
import com.tbielski.GamesProject.domain.entity.game.Game;
import com.tbielski.GamesProject.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class GameController {

    private final GameService gameService;

    public GameController(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/api/games")
    List<DtoGameView> getAllGames() {
        return this.gameService.getAllGamesDto();
    }

    @GetMapping("/api/games/{id}")
    DtoGameView getGameById(@PathVariable @NotNull Long id) {
        return this.gameService.gameToDto(this.gameService.getGameById(id));
    }

    @PostMapping("/api/games/add")
    void addNewGame(@RequestBody @Valid DtoAddGame dtoGameToAdd) {
        Game gameToAdd = gameService.gameFromDto(dtoGameToAdd);
        this.gameService.addNewGame(gameToAdd);
    }

    @DeleteMapping("/api/games/{id}")
    void deleteGameById(@PathVariable @NotNull Long id) {
        this.gameService.deleteGameById(id);
    }

    @PostMapping("/api/games/edit")
    void editGame(@RequestBody @Valid DtoEditGame game) {
        this.gameService.editGame(game);
    }

}