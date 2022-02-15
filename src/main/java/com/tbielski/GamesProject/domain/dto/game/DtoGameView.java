package com.tbielski.GamesProject.domain.dto.game;

import com.tbielski.GamesProject.domain.dto.platform.DtoPlatformView;
import com.tbielski.GamesProject.domain.entity.game.Game;
import com.tbielski.GamesProject.domain.entity.platform.Platform;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public @Data
class DtoGameView {
    private Long id;
    private String name;
    private String type;
    private Integer releaseYear;
    private Integer rating;
    private String developerName;
    private Set<DtoPlatformView> platforms;
    private Integer gameTime;
    private Integer achievementsToEarn;
    private Integer achievementsEarned;

    public DtoGameView() {
    }

    public DtoGameView(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.type = game.getType();
        this.releaseYear = game.getReleaseYear();
        this.rating = game.getRating();
        this.developerName = game.getDeveloper().getName();
        this.platforms = this.allPlatformsDto(game.getPlatforms());
        this.gameTime = game.getStatistics().getGameTime();
        this.achievementsToEarn = game.getStatistics().getAchievementsToEarn();
        this.achievementsEarned = game.getStatistics().getAchievementsEarned();
    }

    public Set<DtoPlatformView> allPlatformsDto(Set<Platform> platforms) {
        Set<DtoPlatformView> allPlatformsDto = new HashSet<DtoPlatformView>();
        for (Platform platform : platforms) {
            allPlatformsDto.add(this.platformToDto(platform));
        }
        return allPlatformsDto;
    }

    public DtoPlatformView platformToDto(Platform platform) {
        return new DtoPlatformView(platform);
    }
}