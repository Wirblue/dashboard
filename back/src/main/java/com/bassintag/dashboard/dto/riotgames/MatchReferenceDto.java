package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

/**
 * MatchReferenceDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
public class MatchReferenceDto {

    private String lane;

    private long gameId;

    private int champion;

    private String platformId;

    private int season;

    private int queue;

    private String role;

    private long timestamp;
}
