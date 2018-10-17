package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.RefreshTimeDto;
import com.bassintag.dashboard.dto.RenderedWidgetDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.model.WidgetSubscription;
import com.bassintag.dashboard.service.UserService;
import com.bassintag.dashboard.service.WidgetSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public RenderedWidgetDto updateSubscription(@PathVariable long id, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return widgetSubscriptionService.render(widgetSubscriptionService.getByUserAndId(user, id));
    }

    @PutMapping("/{id}")
    public WidgetSubscriptionDto updateSubscription(@PathVariable long id, @Valid RefreshTimeDto data, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return widgetSubscriptionService.updateRefreshTimeByUserAndId(user, id, data.getRefreshTime());
    }

    @DeleteMapping("/{id}")
    public WidgetSubscriptionDto deleteSubscription(@PathVariable long id, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return widgetSubscriptionService.deleteByUserAndId(user, id);
    }
}
