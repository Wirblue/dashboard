package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

/**
 * SummonerDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
public class SummonerDto {

    private String profileIconId;

    private String name;

    private int summonerLevel;

    private int id;

    private long accountId;

    private long revisionDate;
}
