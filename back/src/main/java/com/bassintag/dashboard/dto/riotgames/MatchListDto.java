package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

import java.util.List;

/**
 * MatchListDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
public class MatchListDto {

    private List<MatchReferenceDto> matches;

    private int totalGames;

    private int startIndex;

    private int endIndex;
}
