package com.bassintag.dashboard.controller.auth;

import com.bassintag.dashboard.dto.AuthRedirectionDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.AccessTokenService;
import com.bassintag.dashboard.service.OAuthRequestTokenService;
import com.bassintag.dashboard.service.UserService;
import com.bassintag.dashboard.service.auth.IOAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

/**
 * AuthController.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final List<IOAuthService> services;

    private final UserService userService;

    private final OAuthRequestTokenService oAuthRequestTokenService;

    private final AccessTokenService accessTokenService;

    @Autowired
    public AuthController(List<IOAuthService> services, UserService userService, OAuthRequestTokenService oAuthRequestTokenService, AccessTokenService accessTokenService) {
        this.services = services;
        this.userService = userService;
        this.oAuthRequestTokenService = oAuthRequestTokenService;
        this.accessTokenService = accessTokenService;
    }

    private IOAuthService getService(String name) {
        return services.stream().filter(s -> s.getName().equals(name)).findFirst()
                .orElseThrow(() -> new NotFoundException("Cannot find auth service: " + name));
    }

    @GetMapping(params = {"externalService", "redirectUri"})
    public RedirectView auth(@RequestParam String externalService,
                             @RequestParam String redirectUri,
                             Principal principal) throws JsonProcessingException {
        User user = userService.getUserByUsername(principal.getName());
        IOAuthService service = getService(externalService);
        ObjectMapper mapper = new ObjectMapper();
        AuthRedirectionDto authRedirection = new AuthRedirectionDto(
                externalService, redirectUri, user.getUsername(), oAuthRequestTokenService.insertSecret(user, externalService));
        String json = mapper.writer().writeValueAsString(authRedirection);
        String base64 = Base64.getEncoder().encodeToString(json.getBytes());
        return new RedirectView(service.getAuthUri(base64));
    }

    @GetMapping(value = "/callback", params = {"state", "code"})
    public RedirectView authCallback(@RequestParam String state,
                                     @RequestParam String code) {
        String json = new String(Base64.getDecoder().decode(state.getBytes()));
        ObjectMapper mapper = new ObjectMapper();
        AuthRedirectionDto authRedirection;
        try {
            authRedirection = mapper.readValue(json, AuthRedirectionDto.class);
        } catch (IOException e) {
            throw new BadRequestException("Invalid state");
        }
        oAuthRequestTokenService.validateSecret(authRedirection);
        IOAuthService service = getService(authRedirection.getExternalService());
        accessTokenService.saveAccessToken(service,
                userService.getUserByUsername(authRedirection.getUsername()),
                code);
        return new RedirectView(authRedirection.getRedirectionUri());
    }
}
