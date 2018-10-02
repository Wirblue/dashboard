package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.*;
import com.bassintag.dashboard.service.IService;
import com.bassintag.dashboard.service.WeatherService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * AboutEndpoint.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@RestController
public class AboutController {

    private final ServerDto serverDtoCache;

    private final WeatherService weatherService;

    public AboutController(@Autowired List<? extends IService> services,
                           @Autowired WeatherService weatherService) {
        this.weatherService = weatherService;
        serverDtoCache = new ServerDto();
        serverDtoCache.setServices(services.stream().map(s -> {
            ServiceDto dto = new ServiceDto();
            dto.setWidgets(Arrays.stream(s.getWidgets()).map(w -> {
                WidgetDto wDto = new WidgetDto();
                wDto.setName(w.getName());
                wDto.setDescription(w.getDescription());
                wDto.setParams(w.getParams());
                return wDto;
            }).toArray(WidgetDto[]::new));
            dto.setName(s.getName());
            return dto;
        }).toArray(ServiceDto[]::new));
    }

    @GetMapping("/about.json")
    public AboutDto about(HttpServletRequest request) {
        ClientDto clientDto = new ClientDto(request.getRemoteAddr());
        return new AboutDto(clientDto, serverDtoCache);
    }

    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }

    @GetMapping("/services")
    public ServiceDto[] services() {
        return serverDtoCache.getServices();
    }
}
