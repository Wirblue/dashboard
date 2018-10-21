package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.TwitchConfiguration;
import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.auth.TwitchAuthService;
import me.philippheuer.twitch4j.TwitchClient;
import me.philippheuer.twitch4j.auth.model.OAuthCredential;
import me.philippheuer.twitch4j.enums.Endpoints;
import me.philippheuer.twitch4j.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

@Service
public class TwitchApplicationService extends ApplicationService{

    private final TwitchConfiguration configuration;
    private final TwitchClient client;

    @Autowired
    protected TwitchApplicationService(TwitchAuthService twitchAuthService, TwitchConfiguration configuration, TwitchClient client) {
        super("twitch", twitchAuthService, new Color(243,229,245));
        this.configuration = configuration;
        this.client = client;
    }

    private OAuthCredential getCredentials(User user) {
        AccessToken accessToken = getAuthService().getAccessToken(user);
        OAuthCredential credential = new OAuthCredential();
        credential.setToken(accessToken.getAccessToken());
        credential.setRefreshToken(accessToken.getRefreshToken());
        return credential;
    }

    public Channel getUserChannel(User user)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", getHeaders(user));
        return restTemplate.exchange(Endpoints.API.getURL() + "/channel", HttpMethod.GET, entity, Channel.class).getBody();
    }

    private HttpHeaders getHeaders(User user)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "OAuth " + getCredentials(user).getToken());
        return httpHeaders;

    }
}
