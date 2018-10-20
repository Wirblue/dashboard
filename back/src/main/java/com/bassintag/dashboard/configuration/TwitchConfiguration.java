package com.bassintag.dashboard.configuration;

import lombok.Data;
import me.philippheuer.twitch4j.TwitchClient;
import me.philippheuer.twitch4j.TwitchClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TwitchConfiguration {

    @Value("${app.auth.twitch.id}")
    private String id;

    @Value("${app.auth.twitch.secret}")
    private String secret;

    @Value("${app.auth.redirect-url}")
    private String redirect;

    public TwitchClientBuilder builder()
    {
        return TwitchClientBuilder.init().withClientId(id).withClientSecret(secret);
    }

    @Bean
    public TwitchClient twitchClient() {
        return builder().build();
    }
}
