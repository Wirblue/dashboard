package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

/**
 * ParticipantStatsDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 10/10/2018
 */
@Data
public class ParticipantStatsDto {

    private boolean win;

    private int kills;

    private int deaths;

    private int assists;
}
