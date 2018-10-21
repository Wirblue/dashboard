package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

@Data
public class ChampionMasteryDto {

    private int championLevel;

    private boolean chestGranted;

    private long championPoints;

    private long championPointsSinceLastLevel;

    private int tokensEarned;

    private int championId;

    private long lastPlayTime;
}
