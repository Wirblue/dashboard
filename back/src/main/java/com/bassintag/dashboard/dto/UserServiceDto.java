package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.IApplicationService;
import lombok.Getter;
import lombok.Setter;

public class UserServiceDto extends ServiceDto {

    public UserServiceDto(IApplicationService service, User user) {
        super(service);
        if (service.getAuthService() != null)
            authService = new AuthServiceDto(service.getAuthService(), user);
    }

    @Getter
    @Setter
    private AuthServiceDto authService;
}
