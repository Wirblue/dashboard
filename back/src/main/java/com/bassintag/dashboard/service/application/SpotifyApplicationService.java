package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.SpotifyConfiguration;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.auth.SpotifyAuthService;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
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
        super("spotify", authService, new Color(232, 245, 233));
        this.configuration = configuration;
    }

    private SpotifyApi getClient(User user) {
        AccessToken accessToken = getAuthService().getAccessToken(user);
        SpotifyApi spotifyApi = configuration.builder()
                .setAccessToken(accessToken.getAccessToken())
                .setRefreshToken(accessToken.getRefreshToken())
                .build();
        try {
            AuthorizationCodeCredentials credentials = spotifyApi.authorizationCodeRefresh().build().execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());
            spotifyApi.setRefreshToken(credentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException e) {
            throw new BadRequestException("Bad response from spotify");
        }
        return spotifyApi;
    }

    public Artist getTopArtist(User user) {
        SpotifyApi client = getClient(user);
        try {
            return client.getUsersTopArtists().limit(1).build().execute().getItems()[0];
        } catch (IOException | SpotifyWebApiException e) {
            throw new BadRequestException("Bad response from spotify");
        }
    }

    public Track getTopTrack(User user) {
        SpotifyApi client = getClient(user);
        try {
            return client.getUsersTopTracks().limit(1).build().execute().getItems()[0];
        } catch (IOException | SpotifyWebApiException e) {
            throw new BadRequestException("Bad response from spotify");
        }
    }

    public AlbumSimplified[] getNews(User user, int size, CountryCode country) {
        SpotifyApi client = getClient(user);
        try {
            return client.getListOfNewReleases().limit(size).offset(0).country(country).build().execute().getItems();
        } catch (IOException | SpotifyWebApiException e) {
            throw new BadRequestException("Bad response from spotify");
        }
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
