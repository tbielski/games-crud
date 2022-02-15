package com.tbielski.GamesProject.domain.dto.platform;

import com.tbielski.GamesProject.domain.entity.platform.Platform;
import lombok.Data;

public @Data
class DtoPlatformView {
    private Long id;
    private String shortcut;
    private String fullName;
    private String type;
    private String producer;
    private Integer releaseYear;

    public DtoPlatformView() {
    }

    public DtoPlatformView(Platform platform) {
        this.id = platform.getId();
        this.shortcut = platform.getShortcut();
        this.fullName = platform.getFullName();
        this.type = platform.getType();
        this.producer = platform.getProducer();
        this.releaseYear = platform.getReleaseYear();
    }
}