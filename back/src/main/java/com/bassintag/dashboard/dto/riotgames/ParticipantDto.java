package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

/**
 * ParticipantDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 10/10/2018
 */
@Data
public class ParticipantDto {

    private ParticipantStatsDto stats;

    private int participantId;

    private int teamId;
}
