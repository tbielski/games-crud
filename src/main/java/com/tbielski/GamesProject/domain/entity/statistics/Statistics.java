package com.tbielski.GamesProject.domain.entity.statistics;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "statistics")
public class Statistics {
    private Long id;

    @NotNull(message = "GameTime field cannot be empty")
    @Min(value = 0, message = "GameTime field cannot go below 0")
    private Integer gameTime;

    @NotNull(message = "AchievementsToEarn field cannot be empty")
    @Min(value = 0, message = "AchievementsToEarn field cannot go below 0")
    private Integer achievementsToEarn;

    @NotNull(message = "AchievementsEarned field cannot be empty")
    @Min(value = 0, message = "AchievementsEarned field cannot go below 0")
    private Integer achievementsEarned;

    public Statistics() {
    }

    public Statistics(Integer gameTime, Integer achievementsToEarn, Integer achievementsEarned) {
        this.gameTime = gameTime;
        this.achievementsToEarn = achievementsToEarn;
        this.achievementsEarned = achievementsEarned;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGameTime() {
        return gameTime;
    }

    public void setGameTime(Integer gameTime) {
        this.gameTime = gameTime;
    }

    public Integer getAchievementsToEarn() {
        return achievementsToEarn;
    }

    public void setAchievementsToEarn(Integer achievementsToEarn) {
        this.achievementsToEarn = achievementsToEarn;
    }

    public Integer getAchievementsEarned() {
        return achievementsEarned;
    }

    public void setAchievementsEarned(Integer achievementsEarned) {
        this.achievementsEarned = achievementsEarned;
    }

}