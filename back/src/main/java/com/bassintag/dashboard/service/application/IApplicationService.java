package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.widget.IWidgetDefinition;

import java.util.Optional;

/**
 * IApplicationService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
public interface IApplicationService {

    String getName();

    IWidgetDefinition[] getWidgets();

    IWidgetDefinition getWidgetByName(String name);
}
