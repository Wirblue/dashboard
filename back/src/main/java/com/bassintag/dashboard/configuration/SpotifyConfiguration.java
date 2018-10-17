package com.bassintag.dashboard.configuration;

import com.wrapper.spotify.SpotifyApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * SpotifyConfiguration.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Configuration
public class SpotifyConfiguration {

    @Value("${app.auth.spotify.id}")
    private String id;

    @Value("${app.auth.spotify.secret}")
    private String secret;

    @Value("${app.auth.redirect-url}")
    private String redirect;

    public SpotifyApi.Builder builder() {
        return SpotifyApi.builder().setClientId(id).setClientSecret(secret);
    }

    @Bean
    public SpotifyApi spotifyApi() throws URISyntaxException {
        return builder().setRedirectUri(new URI(redirect)).build();
    }
}
