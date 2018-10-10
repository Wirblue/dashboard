package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

/**
 * PlayerDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
public class PlayerDto {

    private String currentPlatformId;

    private String summonerName;

    private String matchHistoryUri;

    private String platformId;

    private long currentAccountId;

    private int profileIcon;

    private long summonerId;

    private long accountId;
}
