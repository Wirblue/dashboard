package com.bassintag.dashboard.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * RiotGamesConfiguration.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
@Configuration
public class    RiotGamesConfiguration {

    @Value("${app.riot-games.key}")
    private String key;

    @Value("${app.riot-games.base-url}")
    private String baseUrl;

    @Value("${app.riot-games.static-base-url}")
    private String staticBaseUrl;

}
