package com.bassintag.dashboard.service;

import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.repository.AccessTokenRepository;
import com.bassintag.dashboard.service.auth.IOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * AccessTokenService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Service
public class AccessTokenService {

    private final AccessTokenRepository repository;

    @Autowired
    public AccessTokenService(AccessTokenRepository repository) {
        this.repository = repository;
    }

    public void deleteByUserAndService(User user, String name) {
        repository.findByUserAndService(user, name).ifPresent(t -> repository.deleteById(t.getId()));

    }

    public void updateAccessToken(AccessToken accessToken) {
        repository.save(accessToken);
    }

    public void saveAccessToken(IOAuthService service, User user, String code) {
        AccessToken token;
        long time = System.currentTimeMillis() / 1000;
        try {
            token = service.createAccessToken(code);
        } catch (IOException e) {
            throw new BadRequestException("Invalid oauth code");
        }
        token.setUser(user);
        token.setService(service.getName());
        token.setCreatedAt(time);
        deleteByUserAndService(user, service.getName());
        repository.save(token);
    }

    public AccessToken getByUserAndService(User user, String service) {
        return repository.findByUserAndService(user, service)
                .orElseThrow(() -> new NotFoundException("Could not find Access Token"));
    }
}
