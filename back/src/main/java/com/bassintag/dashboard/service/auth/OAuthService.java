package com.bassintag.dashboard.service.auth;

import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.AccessTokenService;

/**
 * OAuthService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
public abstract class OAuthService implements IOAuthService {

    private final AccessTokenService accessTokenService;

    private final String name;

    protected OAuthService(AccessTokenService accessTokenService, String name) {
        this.accessTokenService = accessTokenService;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AccessToken getAccessToken(User user) {
        return accessTokenService.getByUserAndService(user, name);
    }
}
