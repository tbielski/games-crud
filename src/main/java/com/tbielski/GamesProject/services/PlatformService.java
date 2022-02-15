package com.tbielski.GamesProject.services;

import com.tbielski.GamesProject.domain.entity.platform.Platform;
import com.tbielski.GamesProject.exceptions.NotFoundException;
import com.tbielski.GamesProject.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;

    public PlatformService(@Autowired PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<Platform> getAllPlatforms() {
        return this.platformRepository.findAll();
    }

    public Platform getPlatformById(Long id) {
        return this.platformRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void addNewPlatform(Platform platform) {
        this.platformRepository.save(platform);
    }

    @Transactional
    public void deletePlatformById(Long id) {
        try {
            this.platformRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException();
        }
    }

    @Transactional
    public void editPlatform(Platform platform) {
        Platform platformFinal = this.getPlatformById(platform.getId());
        platformFinal.setShortcut(platform.getShortcut());
        platformFinal.setFullName(platform.getFullName());
        platformFinal.setType(platform.getType());
        platformFinal.setProducer(platform.getProducer());
        platformFinal.setReleaseYear(platform.getReleaseYear());
        this.platformRepository.save(platformFinal);
    }

}