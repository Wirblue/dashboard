package com.bassintag.dashboard.service;

import com.bassintag.dashboard.dto.AuthServiceDto;
import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.auth.IOAuthService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuthServiceService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Service
public class AuthServiceService {

    private final List<IOAuthService> authServices;

    public AuthServiceService(List<IOAuthService> authServices) {
        this.authServices = authServices;
    }

    public IOAuthService getByName(String name) {
        return authServices.stream().filter(s -> s.getName().equals(name)).findFirst()
                .orElseThrow(() -> new NotFoundException("Could not find service with name: " + name));
    }

    public AuthServiceDto[] getAuthServices(User user) {
        return authServices.stream().map(s -> new AuthServiceDto(s, user)).toArray(AuthServiceDto[]::new);
    }
}
