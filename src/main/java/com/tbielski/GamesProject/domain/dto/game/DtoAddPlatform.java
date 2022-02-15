package com.tbielski.GamesProject.domain.dto.game;

import lombok.Data;

public @Data
class DtoAddPlatform {
    private Long id;
    private Long platformId;

    public DtoAddPlatform() {
    }

    public DtoAddPlatform(Long id, Long platformId) {
        this.id = id;
        this.platformId = platformId;
    }
}