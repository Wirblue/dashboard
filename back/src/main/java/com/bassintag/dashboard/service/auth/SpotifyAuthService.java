package com.bassintag.dashboard.service.auth;

import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.service.AccessTokenService;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * SpotifyAuthService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Service
public class SpotifyAuthService extends OAuthService {

    private final SpotifyApi spotifyApi;

    @Autowired
    public SpotifyAuthService(SpotifyApi spotifyApi, AccessTokenService accessTokenService) {
        super(accessTokenService, "spotify");
        this.spotifyApi = spotifyApi;
    }

    @Override
    public String getAuthUri(String state) {
        return spotifyApi
                .authorizationCodeUri()
                .state(state)
                .scope("user-top-read")
                .show_dialog(true)
                .build().execute().toString();
    }

    @Override
    public AccessToken createAccessToken(String code) throws IOException {
        AuthorizationCodeCredentials credentials;
        try {
            credentials = spotifyApi.authorizationCode(code).build().execute();
        } catch (SpotifyWebApiException e) {
            throw new IOException("Invalid code");
        }
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(credentials.getAccessToken());
        accessToken.setRefreshToken(credentials.getRefreshToken());
        return accessToken;
    }
}
