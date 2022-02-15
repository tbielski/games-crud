package com.tbielski.GamesProject.repository;

import com.tbielski.GamesProject.domain.entity.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}