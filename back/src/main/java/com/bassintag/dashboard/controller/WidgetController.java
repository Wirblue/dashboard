package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.RenderedWidgetDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.UserService;
import com.bassintag.dashboard.service.WidgetSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.security.Principal;


/**
 * WidgetController.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@RestController
@RequestMapping("/subscriptions/widgets")
public class WidgetController {

    private final UserService userService;

    private final WidgetSubscriptionService widgetSubscriptionService;

    @Autowired
    public WidgetController(UserService userService, WidgetSubscriptionService widgetSubscriptionService) {
        this.userService = userService;
        this.widgetSubscriptionService = widgetSubscriptionService;
    }

    @GetMapping
    public RenderedWidgetDto[] widgets(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return user.getWidgets().stream().map(widgetSubscriptionService::render).toArray(RenderedWidgetDto[]::new);
    }

    @GetMapping("/{serviceName}")
    public RenderedWidgetDto[] widgets(Principal principal, @PathVariable @NotNull String serviceName) {
        User user = userService.getUserByUsername(principal.getName());
        return widgetSubscriptionService.render(widgetSubscriptionService.getUserSubscriptions(user, serviceName));
    }

    @GetMapping("/{serviceName}/{widgetName}")
    public RenderedWidgetDto widget(Principal principal,
                                    @PathVariable @NotNull String serviceName,
                                    @PathVariable @NotNull String widgetName) {
        User user = userService.getUserByUsername(principal.getName());
        return widgetSubscriptionService.render(widgetSubscriptionService.getUserSubscription(user, serviceName, widgetName));
    }
}
