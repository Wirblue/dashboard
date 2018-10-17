package com.bassintag.dashboard.service;

import com.bassintag.dashboard.dto.ParamValueDto;
import com.bassintag.dashboard.dto.RenderedWidgetDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.model.WidgetSubscription;
import com.bassintag.dashboard.repository.WidgetSubscriptionRepository;
import com.bassintag.dashboard.widget.IWidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * WidgetSubscriptionService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 04/10/2018
 */
@Service
public class WidgetSubscriptionService {

    private final WidgetSubscriptionRepository widgetSubscriptionRepository;

    private final ApplicationServiceService applicationServiceService;

    private final IWidgetDefinition[] widgets;

    @Autowired
    public WidgetSubscriptionService(WidgetSubscriptionRepository widgetSubscriptionRepository,
                                     ApplicationServiceService applicationServiceService,
                                     IWidgetDefinition[] widgets) {
        this.widgetSubscriptionRepository = widgetSubscriptionRepository;
        this.applicationServiceService = applicationServiceService;
        this.widgets = widgets;
    }

    public WidgetSubscription subscribe(IWidgetDefinition widget, User user, ParamValueDto[] params) {
        WidgetSubscription subscription = widget.subscribe(user, params);
        widgetSubscriptionRepository.save(subscription);
        return subscription;
    }

    public WidgetSubscriptionDto[] getUserSubscriptions(User user) {
        return user.getWidgets().stream().map(WidgetSubscriptionDto::new).toArray(WidgetSubscriptionDto[]::new);
    }

    public WidgetSubscription getByUserAndId(User user, long id) {
        return widgetSubscriptionRepository.getByUserAndId(user, id)
                .orElseThrow(() -> new NotFoundException("Could not find subscription with id: " + id));
    }

    public WidgetSubscriptionDto deleteByUserAndId(User user, long id) {
        WidgetSubscriptionDto ret = new WidgetSubscriptionDto(getByUserAndId(user, id));
        widgetSubscriptionRepository.deleteById(ret.getId());
        return ret;
    }

    public WidgetSubscriptionDto updateRefreshTimeByUserAndId(User user, long id, long refreshTime) {
        WidgetSubscription subscription = getByUserAndId(user, id);
        subscription.setRefreshTime(refreshTime);
        return new WidgetSubscriptionDto(subscription);
    }

    public WidgetSubscription[] getUserSubscriptions(User user, String service) {
        List<WidgetSubscription> subscriptions = widgetSubscriptionRepository.getAllByUserAndServiceName(user, service);
        return subscriptions.toArray(new WidgetSubscription[0]);
    }


    public WidgetSubscription getUserSubscription(User user, String service, String name) {
        WidgetSubscription subscription = widgetSubscriptionRepository.getByUserAndServiceNameAndWidgetName(user, service, name);
        if (subscription == null)
            throw new NotFoundException("Could not find widget subscription with service: " + service + " and name: " + name);
        return subscription;
    }

    public RenderedWidgetDto render(WidgetSubscription widgetSubscription) {
        ParamValueDto[] params = widgetSubscription.getParams().stream().map(ParamValueDto::new).toArray(ParamValueDto[]::new);
        Optional<IWidgetDefinition> widget = Arrays.stream(widgets)
                .filter(w -> w.getService().getName().equals(widgetSubscription.getServiceName()) && w.getName().equals(widgetSubscription.getWidgetName()))
                .findFirst();
        if (!widget.isPresent()) {
            throw new BadRequestException("Invalid widget with service: " + widgetSubscription.getServiceName() + " and name: " + widgetSubscription.getWidgetName());
        }
        return widget.get().render(widgetSubscription.getUser(), params);
    }

    public RenderedWidgetDto[] render(WidgetSubscription[] widgetSubscriptions) {
        return Arrays.stream(widgetSubscriptions).map(this::render).toArray(RenderedWidgetDto[]::new);
    }
}
