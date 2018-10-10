package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

import java.util.List;

/**
 * MatchDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
public class MatchDto {

    private int seasonId;

    private int queueId;

    private long gameId;

    private List<ParticipantIdentityDto> participantIdentities;

    private String gameVersion;

    private String platformId;

    private String gameMode;

    private int mapId;

    private String gameType;

    private List<TeamStatsDto> teams;

    private List<ParticipantDto> participants;

    private long gameDuration;

    private long gameCreation;

    public ParticipantDto getParticipantByAccountId(long accountId) {
        ParticipantIdentityDto participantIdentity = participantIdentities.stream()
                .filter(p -> p.getPlayer().getAccountId() == accountId).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid accountId for match: " + accountId));
        return participants.stream().filter(id -> id.getParticipantId() == participantIdentity.getParticipantId())
                .findFirst().orElseThrow(() -> new RuntimeException("Could not find participant"));
    }

    public boolean won(long accountId) {
        ParticipantDto participantDto = getParticipantByAccountId(accountId);
        return teams.stream().filter(t -> t.getTeamId() == participantDto.getTeamId()).findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find team"))
                .getWin().startsWith("W");
    }
}
