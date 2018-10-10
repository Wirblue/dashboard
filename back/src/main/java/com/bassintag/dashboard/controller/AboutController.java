package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.AboutDto;
import com.bassintag.dashboard.dto.ClientDto;
import com.bassintag.dashboard.dto.ServerDto;
import com.bassintag.dashboard.dto.ServiceDto;
import com.bassintag.dashboard.service.ApplicationServiceService;
import com.bassintag.dashboard.service.application.IApplicationService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final ApplicationServiceService applicationServiceService;

    @Autowired
    public AboutController(ApplicationServiceService applicationServiceService) {
        this.applicationServiceService = applicationServiceService;
    }

    @GetMapping("/about.json")
    public AboutDto about(HttpServletRequest request) {
        ClientDto clientDto = new ClientDto(request.getRemoteAddr());
        return new AboutDto(clientDto, new ServerDto(Arrays.stream(applicationServiceService.getApplicationServices()).map(ServiceDto::new).toArray(ServiceDto[]::new)));
    }
}
