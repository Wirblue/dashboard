package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

/**
 * ParticipantIdentityDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
public class ParticipantIdentityDto {

    private int participantId;

    private PlayerDto player;
}
