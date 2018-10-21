package com.bassintag.dashboard.dto.twitch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class GameDto {

    @JsonProperty("_id")
    private Long id;

    private String name;

    private GameDto.GameBox box;

    private GameDto.GameLogo logo;

    private long giantbombId;

    private int popularity; // From search results

    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class GameBox {
        private String large;
        private String medium;
        private String small;
        private String template;
    }

    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class GameLogo {
        private String large;
        private String medium;
        private String small;
        private String template;
    }
}
