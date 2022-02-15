package com.tbielski.GamesProject.domain.entity.game;

import com.tbielski.GamesProject.domain.entity.developer.Developer;
import com.tbielski.GamesProject.domain.entity.platform.Platform;
import com.tbielski.GamesProject.domain.entity.statistics.Statistics;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
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
    private Developer developer;
    private Set<Platform> platforms;
    private Statistics statistics;

    public Game() {
    }

    public Game(String name, String type, Integer releaseYear, Integer rating, Developer developer, Statistics statistics) {
        this.name = name;
        this.type = type;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.developer = developer;
        this.platforms = new HashSet<Platform>();
        this.statistics = statistics;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
    }

    public void removePlatform(Platform platform) {
        this.platforms.remove(platform);
    }

    @OneToOne
    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

}