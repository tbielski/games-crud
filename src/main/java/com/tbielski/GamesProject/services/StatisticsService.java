package com.tbielski.GamesProject.services;

import com.tbielski.GamesProject.domain.entity.statistics.Statistics;
import com.tbielski.GamesProject.exceptions.NotFoundException;
import com.tbielski.GamesProject.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public StatisticsService(@Autowired StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public List<Statistics> getAllStatistics() {
        return this.statisticsRepository.findAll();
    }

    public Statistics getStatisticsById(Long id) {
        return this.statisticsRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void addNewStatistics(Statistics statistics) {
        this.statisticsRepository.save(statistics);
    }

    @Transactional
    public void deleteStatisticsById(Long id) {
        try {
            this.statisticsRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException();
        }
    }

}