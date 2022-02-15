package com.tbielski.GamesProject.domain.entity.platform;

import com.tbielski.GamesProject.domain.entity.game.Game;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "platforms")
public class Platform {
    private Long id;

    @Size(min = 2, max = 10, message = "Shortcut length must be between 2 and 10 characters")
    private String shortcut;

    @NotBlank(message = "FullName field cannot be empty")
    private String fullName;

    @NotBlank(message = "Type field cannot be empty")
    private String type;

    @NotBlank(message = "Producer field cannot be empty")
    private String producer;

    @NotNull(message = "ReleaseYear field cannot be empty")
    @Min(value = 1900, message = "ReleaseYear field cannot go below 1900")
    @Max(value = 2022, message = "ReleaseYear field cannot exceed 2022")
    private Integer releaseYear;
    private Set<Game> platformGames;

    public Platform() {
    }

    public Platform(String shortcut, String fullName, String type, String producer, Integer releaseYear) {
        this.shortcut = shortcut;
        this.fullName = fullName;
        this.type = type;
        this.producer = producer;
        this.releaseYear = releaseYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @ManyToMany(mappedBy = "platforms", fetch = FetchType.EAGER)
    public Set<Game> getPlatformGames() {
        return platformGames;
    }

    public void setPlatformGames(Set<Game> platformGames) {
        this.platformGames = platformGames;
    }

}