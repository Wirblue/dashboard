package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.exception.NotFoundException;
import com.bassintag.dashboard.widget.IWidgetDefinition;

import java.util.Arrays;
import java.util.Optional;

/**
 * ApplicationService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
public abstract class ApplicationService implements IApplicationService {

    private final IWidgetDefinition[] widgets;

    private final String name;

    protected ApplicationService(String name) {
        widgets = setupWidgets();
        this.name = name;
    }

    protected abstract IWidgetDefinition[] setupWidgets();

    @Override
    public IWidgetDefinition getWidgetByName(String name) {
        Optional<IWidgetDefinition> widget = Arrays.stream(getWidgets()).filter(w -> w.getName().equals(name)).findFirst();
        if (!widget.isPresent()) {
            throw new NotFoundException("No widget could be found with name: " + name);
        }
        return widget.get();
    }

    @Override
    public IWidgetDefinition[] getWidgets() {
        return widgets;
    }

    @Override
    public String getName() {
        return name;
    }
}
