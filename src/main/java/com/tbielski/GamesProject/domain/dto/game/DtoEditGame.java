package com.tbielski.GamesProject.domain.dto.game;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public @Data
class DtoEditGame {
    private Long id;

    @NotBlank(message = "Name field cannot be empty")
    private String name;

    @NotBlank(message = "Type field cannot be empty")
    private String type;

    @NotNull(message = "ReleaseYear field cannot be empty")
    @Min(value = 1945, message = "ReleaseYear field cannot go below 1945")
    @Max(value = 2022, message = "ReleaseYear field cannot exceed 2022")
    private Integer releaseYear;

    @NotNull(message = "Rating field cannot be empty")
    @Min(value = 0, message = "Rating field cannot go below 0")
    @Max(value = 10, message = "Rating field cannot exceed 10")
    private Integer rating;
    private Long developerId;
    private Integer gameTime;
    private Integer achievementsEarned;

    public DtoEditGame() {
    }

    public DtoEditGame(Long id, String name, String type, Integer releaseYear, Integer rating, Long developerId, Integer gameTime, Integer achievementsEarned) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.developerId = developerId;
        this.gameTime = gameTime;
        this.achievementsEarned = achievementsEarned;
    }
}