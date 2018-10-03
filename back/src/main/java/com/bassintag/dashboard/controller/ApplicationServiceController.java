package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.ServiceDto;
import com.bassintag.dashboard.dto.WidgetDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.ApplicationServiceService;
import com.bassintag.dashboard.service.UserService;
import com.bassintag.dashboard.widget.IWidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;

@RestController
@RequestMapping("/services")
public class ApplicationServiceController {

    private final ApplicationServiceService applicationServiceService;

    private final UserService userService;

    @Autowired
    public ApplicationServiceController(ApplicationServiceService applicationServiceService, UserService userService) {
        this.applicationServiceService = applicationServiceService;
        this.userService = userService;
    }


    @GetMapping
    public ServiceDto[] services() {
        return Arrays.stream(applicationServiceService.getApplicationServices())
                .map(ServiceDto::new).toArray(ServiceDto[]::new);
    }

    @GetMapping("/{serviceName}")
    public ServiceDto service(@PathVariable String serviceName) {
        return new ServiceDto(applicationServiceService.getServiceByName(serviceName));
    }

    @GetMapping("/{serviceName]/widgets")
    public WidgetDto[] widget(@PathVariable String serviceName) {
        return Arrays.stream(applicationServiceService.getServiceByName(serviceName).getWidgets())
                .map(WidgetDto::new).toArray(WidgetDto[]::new);
    }

    @GetMapping("/{serviceName}/widgets/{widgetName}")
    public WidgetDto widget(@PathVariable String serviceName, @PathVariable String widgetName) {
        return new WidgetDto(applicationServiceService.getServiceByName(serviceName).getWidgetByName(widgetName));
    }

    @GetMapping("/{serviceName}/widgets/{widgetName}/subscribe")
    public WidgetDto widgetDto(@PathVariable String serviceName, @PathVariable String widgetName, Principal principal) {
        IWidgetDefinition widget = applicationServiceService.getServiceByName(serviceName).getWidgetByName(widgetName);
        User user = userService.getUserByUsername(principal.getName());
        if (user.getWidgets().stream().anyMatch(w -> w.getServiceName().equals(serviceName) && w.getWidgetName().equals(widgetName))) {
            throw new BadRequestException("You are already subscribed to this widget");
        }
        return new WidgetDto(widget);
    }
}
