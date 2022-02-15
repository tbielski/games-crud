package com.tbielski.GamesProject.domain.entity.developer;

import com.tbielski.GamesProject.domain.entity.game.Game;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer {
    private Long id;

    @NotBlank(message = "Name field cannot be empty")
    private String name;

    @NotBlank(message = "Publisher field cannot be empty")
    private String publisher;

    @NotNull(message = "CreationYear field cannot be empty")
    @Min(value = 1900, message = "CreationYear field cannot go below 1900")
    @Max(value = 2022, message = "CreationYear field cannot exceed 2022")
    private Integer creationYear;

    @NotBlank(message = "Nationality field cannot be empty")
    private String nationality;
    private Set<Game> games;

    public Developer() {
    }

    public Developer(String name, String publisher, Integer creationYear, String nationality) {
        this.name = name;
        this.publisher = publisher;
        this.creationYear = creationYear;
        this.nationality = nationality;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @OneToMany(mappedBy = "developer", fetch = FetchType.EAGER)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

}