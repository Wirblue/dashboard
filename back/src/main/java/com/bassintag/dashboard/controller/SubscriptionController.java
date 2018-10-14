package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.WidgetSubscriptionDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.UserService;
import com.bassintag.dashboard.service.WidgetSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * SubscriptionsController.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final UserService userService;

    private final WidgetSubscriptionService widgetSubscriptionService;

    @Autowired
    public SubscriptionController(UserService userService, WidgetSubscriptionService widgetSubscriptionService) {
        this.userService = userService;
        this.widgetSubscriptionService = widgetSubscriptionService;
    }

    @GetMapping
    public WidgetSubscriptionDto[] subscriptions(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return widgetSubscriptionService.getUserSubscriptions(user);
    }
}
