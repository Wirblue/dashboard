package com.bassintag.dashboard.service;

import com.bassintag.dashboard.dto.AuthRedirectionDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.OAuthRequest;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.repository.OAuthRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * OAuthRequestTokenService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Service
public class OAuthRequestTokenService {

    private final OAuthRequestRepository repository;

    private final UserService userService;

    @Autowired
    public OAuthRequestTokenService(OAuthRequestRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public String insertSecret(User user, String service) {
        OAuthRequest oAuthRequest = new OAuthRequest();
        oAuthRequest.setService(service);
        oAuthRequest.setUser(user);
        oAuthRequest.setExpiry(new Date(Calendar.getInstance().getTimeInMillis() + 10 * 60 * 1000));
        oAuthRequest.setSecret(UUID.randomUUID().toString());
        repository.save(oAuthRequest);
        return oAuthRequest.getSecret();
    }

    public void validateSecret(AuthRedirectionDto data) {
        User user = userService.getUserByUsername(data.getUsername());
        List<OAuthRequest> requests = repository.findAllByUserAndService(user, data.getExternalService());
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        if (requests.stream().noneMatch(r -> r.getExpiry().after(now) && r.getSecret().equals(data.getSecret())))
            throw new BadRequestException("Invalid or expired secret");
    }
}
