package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.service.auth.IOAuthService;
import com.bassintag.dashboard.widget.IWidgetDefinition;

import java.awt.*;

/**
 * IApplicationService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
public interface IApplicationService {

    String getName();

    void registerWidget(IWidgetDefinition widget);

    IWidgetDefinition[] getWidgets();

    IOAuthService getAuthService();

    Color getDisplayColor();
}
