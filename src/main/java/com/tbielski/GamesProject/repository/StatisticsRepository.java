package com.tbielski.GamesProject.repository;

import com.tbielski.GamesProject.domain.entity.statistics.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}