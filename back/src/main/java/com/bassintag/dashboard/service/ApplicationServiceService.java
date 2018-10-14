package com.bassintag.dashboard.service;

import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.service.application.IApplicationService;
import com.bassintag.dashboard.widget.IWidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * ApplicationServiceService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
@Service
public class ApplicationServiceService {

    private final IApplicationService[] services;

    private final IWidgetDefinition[] widgets;

    @Autowired
    public ApplicationServiceService(IApplicationService[] services, IWidgetDefinition[] widgets) {
        this.services = services;
        this.widgets = widgets;
    }

    public IApplicationService[] getApplicationServices() {
        return services;
    }

    public IApplicationService getServiceByName(String name) {
        Optional<IApplicationService> optional = Arrays.stream(services).filter(s -> s.getName().equals(name)).findFirst();
        if (!optional.isPresent()) {
            throw new NotFoundException("Cannot find any service with username: " + name);
        }
        return optional.get();
    }

    public IWidgetDefinition[] getWidgetsByServiceName(String name) {
        return Arrays.stream(widgets).filter(w -> w.getService().getName().equals(name)).toArray(IWidgetDefinition[]::new);
    }

    public IWidgetDefinition getWidgetByServiceNameAndWidgetName(String serviceName, String widgetName) {
        Optional<IWidgetDefinition> widget = Arrays.stream(widgets).filter(w -> w.getService().getName().equals(serviceName) && w.getName().equals(widgetName)).findFirst();
        if (!widget.isPresent()) {
            throw new NotFoundException("Cannot find any widget with service: " + serviceName + " and name: " + widgetName);
        }
        return widget.get();
    }


}
