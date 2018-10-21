package com.bassintag.dashboard.controller.api;

import com.bassintag.dashboard.dto.*;
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
@RequestMapping("/api/services")
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
    public UserServiceDto[] services(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return Arrays.stream(applicationServiceService.getApplicationServices())
                .map(s -> new UserServiceDto(s, user)).toArray(UserServiceDto[]::new);
    }

    @GetMapping("/{serviceName}")
    public UserServiceDto service(@PathVariable String serviceName, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return new UserServiceDto(applicationServiceService.getServiceByName(serviceName), user);
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
        return new WidgetSubscriptionDto(widget, widgetSubscriptionService.subscribe(widget, user, paramList.getParams()));
    }
}
