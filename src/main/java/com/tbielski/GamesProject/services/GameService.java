package com.tbielski.GamesProject.services;

import com.tbielski.GamesProject.domain.dto.game.DtoAddGame;
import com.tbielski.GamesProject.domain.dto.game.DtoAddPlatform;
import com.tbielski.GamesProject.domain.dto.game.DtoEditGame;
import com.tbielski.GamesProject.domain.dto.game.DtoGameView;
import com.tbielski.GamesProject.domain.entity.developer.Developer;
import com.tbielski.GamesProject.domain.entity.game.Game;
import com.tbielski.GamesProject.domain.entity.platform.Platform;
import com.tbielski.GamesProject.domain.entity.statistics.Statistics;
import com.tbielski.GamesProject.exceptions.NotFoundException;
import com.tbielski.GamesProject.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final DeveloperService developerService;
    private final PlatformService platformService;
    private final StatisticsService statisticsService;

    public GameService(@Autowired GameRepository gameRepository,
                       @Autowired DeveloperService developerService,
                       @Autowired PlatformService platformService,
                       @Autowired StatisticsService statisticsService) {
        this.gameRepository = gameRepository;
        this.developerService = developerService;
        this.platformService = platformService;
        this.statisticsService = statisticsService;
    }

    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    public List<DtoGameView> getAllGamesDto() {
        List<DtoGameView> allGamesDto = new ArrayList<DtoGameView>();
        for (Game game : this.gameRepository.findAll()) {
            allGamesDto.add(this.gameToDto(game));
        }
        return allGamesDto;
    }

    public Game getGameById(Long id) {
        return this.gameRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void addNewGame(Game game) {
        this.gameRepository.save(game);
    }

    @Transactional
    public void deleteGameById(Long id) {
        Long statisticsId = this.getGameById(id).getStatistics().getId();
        this.statisticsService.deleteStatisticsById(statisticsId);
        try {
            this.gameRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException();
        }
    }

    @Transactional
    public void editGame(DtoEditGame dtoGame) {
        Game gameFinal = this.getGameById(dtoGame.getId());
        gameFinal.setName(dtoGame.getName());
        gameFinal.setType(dtoGame.getType());
        gameFinal.setReleaseYear(dtoGame.getReleaseYear());
        gameFinal.setRating(dtoGame.getRating());
        gameFinal.getStatistics().setGameTime(dtoGame.getGameTime());
        gameFinal.getStatistics().setAchievementsEarned(dtoGame.getAchievementsEarned());
        this.gameRepository.save(gameFinal);
    }

    @Transactional
    public void addPlatform(DtoAddPlatform dtoAddPlatform) {
        Game game = this.getGameById(dtoAddPlatform.getId());
        Platform platform = this.platformService.getPlatformById(dtoAddPlatform.getPlatformId());
        game.addPlatform(platform);
        this.gameRepository.save(game);
    }

    @Transactional
    public void deletePlatformGameById(Long idGame, Long idPlatform) {
        Game game = this.getGameById(idGame);
        Platform platform = this.platformService.getPlatformById(idPlatform);
        game.removePlatform(platform);
        this.gameRepository.save(game);
    }

    public Game gameFromDto(DtoAddGame dtoGame) {
        Statistics statistics = new Statistics(0, dtoGame.getAchievementsToEarn(), 0);
        this.statisticsService.addNewStatistics(statistics);
        Developer developer = this.developerService.getDeveloperById(dtoGame.getDeveloperId());
        Platform platform = this.platformService.getPlatformById(dtoGame.getPlatformId());
        Game game = new Game(dtoGame.getName(), dtoGame.getType(), dtoGame.getReleaseYear(), dtoGame.getRating(), developer, statistics);
        game.addPlatform(platform);
        return game;
    }

    public DtoGameView gameToDto(Game game) {
        return new DtoGameView(game);
    }

}
