package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.auth.IOAuthService;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AuthServiceDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Data
@AllArgsConstructor
public class AuthServiceDto {

    private String name;

    private boolean registered;

    public AuthServiceDto(IOAuthService service, User user)
    {
        name = service.getName();
        registered = user.getTokens().stream().anyMatch(t -> t.getService().equals(name));
    }
}
