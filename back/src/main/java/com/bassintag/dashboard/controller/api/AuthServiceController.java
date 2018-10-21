package com.bassintag.dashboard.controller.api;

import com.bassintag.dashboard.dto.AuthServiceDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.AccessTokenService;
import com.bassintag.dashboard.service.AuthServiceService;
import com.bassintag.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * AuthServiceController.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@RestController
@RequestMapping("/api/authorizations")
public class AuthServiceController {

    private final AuthServiceService authServiceService;

    private final AccessTokenService accessTokenService;

    private final UserService userService;

    @Autowired
    public AuthServiceController(AuthServiceService authServiceService,
                                 AccessTokenService accessTokenService,
                                 UserService userService) {
        this.authServiceService = authServiceService;
        this.accessTokenService = accessTokenService;
        this.userService = userService;
    }

    @GetMapping
    public AuthServiceDto[] authServices(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return authServiceService.getAuthServices(user);
    }

    @GetMapping("/{name}")
    public AuthServiceDto authServiceByName(@PathVariable String name, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return new AuthServiceDto(authServiceService.getByName(name), user);
    }

    @DeleteMapping("/{name}")
    public AuthServiceDto deleteAuthServiceByName(@PathVariable String name, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        AuthServiceDto authServiceDto = new AuthServiceDto(authServiceService.getByName(name), user);
        if (!authServiceDto.isRegistered())
            throw new BadRequestException("You are not registered to this authorization service");
        accessTokenService.deleteByUserAndService(user, name);
        authServiceDto.setRegistered(false);
        return authServiceDto;
    }
}
