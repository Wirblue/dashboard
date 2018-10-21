package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.TwitchConfiguration;
import com.bassintag.dashboard.dto.twitch.GameContainerDto;
import com.bassintag.dashboard.dto.twitch.StreamContainerDto;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.net.URI;

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

    public Channel getUserChannel(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", getHeadersAuth(user));
        return restTemplate.exchange(Endpoints.API.getURL() + "/channel", HttpMethod.GET, entity, Channel.class).getBody();
    }

    public GameContainerDto getGames(String name) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", getHeaders());
        String url = Endpoints.API.getURL() + "/search/games?query={name}";
        return restTemplate.exchange(url, HttpMethod.GET, entity, GameContainerDto.class, name).getBody();
    }

    public StreamContainerDto getLive(String game, String language, int limit) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", getHeaders());
        String url = Endpoints.API.getURL() + "/streams?language={language}&game={game}&limit={limit}";
        return restTemplate.exchange(url, HttpMethod.GET, entity, StreamContainerDto.class, language, game, limit).getBody();
    }


    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Client-ID", client.getClientId());
        httpHeaders.set("Accept", "application/vnd.twitchtv.v5+json");
        return httpHeaders;
    }

    private HttpHeaders getHeadersAuth(User user)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "OAuth " + getCredentials(user).getToken());
        return httpHeaders;

    }
}
