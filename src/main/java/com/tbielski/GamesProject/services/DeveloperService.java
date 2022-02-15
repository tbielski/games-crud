package com.tbielski.GamesProject.services;

import com.tbielski.GamesProject.domain.entity.developer.Developer;
import com.tbielski.GamesProject.exceptions.NotFoundException;
import com.tbielski.GamesProject.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public DeveloperService(@Autowired DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> getAllDevelopers() {
        return this.developerRepository.findAll();
    }

    public Developer getDeveloperById(Long id) {
        return this.developerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void addNewDeveloper(Developer developer) {
        this.developerRepository.save(developer);
    }

    @Transactional
    public void deleteDeveloperById(Long id) {
        try {
            this.developerRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException();
        }
    }

    @Transactional
    public void editDeveloper(Developer developer) {
        Developer developerFinal = this.getDeveloperById(developer.getId());
        developerFinal.setName(developer.getName());
        developerFinal.setPublisher(developer.getPublisher());
        developerFinal.setCreationYear(developer.getCreationYear());
        developerFinal.setNationality(developer.getNationality());
        this.developerRepository.save(developerFinal);
    }

}