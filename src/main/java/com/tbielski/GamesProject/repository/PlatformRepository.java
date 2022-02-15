package com.tbielski.GamesProject.repository;

import com.tbielski.GamesProject.domain.entity.platform.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}