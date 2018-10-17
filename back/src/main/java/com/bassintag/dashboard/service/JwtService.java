package com.bassintag.dashboard.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bassintag.dashboard.configuration.JwtConfiguration;
import org.springframework.stereotype.Service;

/**
 * JwtService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Service
public class JwtService {

    private final JwtConfiguration jwtConfiguration;

    public JwtService(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public String verifyToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtConfiguration.getSecret().getBytes()))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject();
    }
}
