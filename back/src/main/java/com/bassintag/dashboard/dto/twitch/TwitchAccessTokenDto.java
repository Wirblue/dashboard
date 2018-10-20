package com.bassintag.dashboard.dto.twitch;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TwitchAccessTokenDto {

    private String accessToken;

    private String refreshToken;

    private long expiresIn;

    private String[] scope;

    private String tokenType;
}
