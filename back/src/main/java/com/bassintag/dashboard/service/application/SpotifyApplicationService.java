package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.SpotifyConfiguration;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.auth.SpotifyAuthService;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * SpotifyApplicationService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Service
public class SpotifyApplicationService extends ApplicationService {

    private final SpotifyConfiguration configuration;

    @Autowired
    protected SpotifyApplicationService(SpotifyAuthService authService, SpotifyConfiguration configuration) {
        super("spotify", authService);
        this.configuration = configuration;
    }

    private SpotifyApi getClient(User user) {
        AccessToken accessToken = getAuthService().getAccessToken(user);
        return configuration.builder()
                .setAccessToken(accessToken.getAccessToken())
                .setRefreshToken(accessToken.getRefreshToken())
                .build();
    }

    public com.wrapper.spotify.model_objects.specification.User getCurrentUser(User user) {
        SpotifyApi api = getClient(user);
        try {
            return api.getCurrentUsersProfile().build().execute();
        } catch (IOException | SpotifyWebApiException e) {
            throw new BadRequestException("Bad response from spotify");
        }
    }
}
