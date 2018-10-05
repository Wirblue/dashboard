package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.widget.IWidgetDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
public abstract class ApplicationService implements IApplicationService {

    private final String name;

    private List<IWidgetDefinition> widgets;

    protected ApplicationService(String name) {
        this.name = name;
        widgets = new ArrayList<>();
    }

    @Override
    public void registerWidget(IWidgetDefinition widget) {
        widgets.add(widget);
    }

    @Override
    public IWidgetDefinition[] getWidgets() {
        return widgets.toArray(new IWidgetDefinition[0]);
    }

    @Override
    public String getName() {
        return name;
    }
}
