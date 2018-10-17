package com.bassintag.dashboard.service.auth;

import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.model.User;

import java.io.IOException;

/**
 * IOAuthService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
public interface IOAuthService {

    String getName();

    String getAuthUri(String state);

    AccessToken createAccessToken(String code) throws IOException;

    AccessToken getAccessToken(User user);

}
