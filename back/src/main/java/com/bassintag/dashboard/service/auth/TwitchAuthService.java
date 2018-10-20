package com.bassintag.dashboard.service.auth;

import com.bassintag.dashboard.configuration.TwitchConfiguration;
import com.bassintag.dashboard.dto.twitch.TwitchAccessTokenDto;
import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.service.AccessTokenService;
import me.philippheuer.twitch4j.TwitchClient;
import me.philippheuer.twitch4j.auth.model.OAuthCredential;
import me.philippheuer.twitch4j.enums.Endpoints;
import me.philippheuer.twitch4j.enums.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TwitchAuthService extends OAuthService{

    private final TwitchClient client;
    private final TwitchConfiguration configuration;

    @Autowired
    protected TwitchAuthService(AccessTokenService accessTokenService, TwitchClient client, TwitchConfiguration configuration) {
        super(accessTokenService, "twitch");
        this.client = client;
        this.configuration = configuration;
    }

    @Override
    public String getAuthUri(String state) {
        return String.format(
                "%s/oauth2/authorize?response_type=code&client_id=%s&redirect_uri=%s&scope=%s&state=%s&force_verify=true",
                Endpoints.API.getURL(),
                client.getCredentialManager().getTwitchClient().getClientId(),
                configuration.getRedirect(),
                Scope.join(Scope.CHANNEL_READ, Scope.USER_READ),
                state
        );
    }

    private TwitchAccessTokenDto getAccessToken(String code)
    {
        String requestUrl = String.format("%s/oauth2/token", Endpoints.API.getURL());
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("grant_type", "authorization_code");
        postParameters.add("client_id", client.getClientId());
        postParameters.add("client_secret", client.getClientSecret());
        postParameters.add("redirect_uri", configuration.getRedirect());
        postParameters.add("code", code);
        return restTemplate.postForObject(requestUrl, postParameters, TwitchAccessTokenDto.class);
    }

    @Override
    public AccessToken createAccessToken(String code) {
        TwitchAccessTokenDto credentials = getAccessToken(code);
        AccessToken token = new AccessToken();
        token.setAccessToken(credentials.getAccessToken());
        token.setRefreshToken(credentials.getRefreshToken());
        return token;
    }
}
