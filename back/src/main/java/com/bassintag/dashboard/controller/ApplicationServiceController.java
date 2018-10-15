package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.ServiceDto;
import com.bassintag.dashboard.dto.WidgetDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.ApplicationServiceService;
import com.bassintag.dashboard.service.UserService;
import com.bassintag.dashboard.service.WidgetSubscriptionService;
import com.bassintag.dashboard.widget.IWidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;

@RestController
@RequestMapping("/services")
public class ApplicationServiceController {

    private final WidgetSubscriptionService widgetSubscriptionService;

    private final ApplicationServiceService applicationServiceService;

    private final UserService userService;

    @Autowired
    public ApplicationServiceController(WidgetSubscriptionService widgetSubscriptionService,
                                        ApplicationServiceService applicationServiceService,
                                        UserService userService) {
        this.widgetSubscriptionService = widgetSubscriptionService;
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
    public WidgetDto[] widgets(@PathVariable String serviceName) {
        return Arrays.stream(applicationServiceService.getWidgetsByServiceName(serviceName))
                .map(WidgetDto::new).toArray(WidgetDto[]::new);
    }

    @GetMapping("/{serviceName}/widgets/{widgetName}")
    public WidgetDto widget(@PathVariable String serviceName, @PathVariable String widgetName) {
        return new WidgetDto(applicationServiceService.getWidgetByServiceNameAndWidgetName(serviceName, widgetName));
    }

    @PostMapping("/{serviceName}/widgets/{widgetName}/subscribe")
    public WidgetSubscriptionDto subscribe(@PathVariable String serviceName, @PathVariable String widgetName,
                                           @RequestBody @Valid WidgetSubscriptionParamsDto paramList, Principal principal) {
        IWidgetDefinition widget = applicationServiceService.getWidgetByServiceNameAndWidgetName(serviceName, widgetName);
        User user = userService.getUserByUsername(principal.getName());
        if (!widget.allowsMultiple() && user.getWidgets().stream().anyMatch(w -> w.getServiceName().equals(serviceName) && w.getWidgetName().equals(widgetName))) {
            throw new BadRequestException("You are already subscribed to this widget");
        }
        return new WidgetSubscriptionDto(widgetSubscriptionService.subscribe(widget, user, paramList.getParams()));
    }
}
