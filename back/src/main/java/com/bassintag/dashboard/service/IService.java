package com.bassintag.dashboard.service;

import com.bassintag.dashboard.widget.IWidgetDefinition;

/**
 * IService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
public interface IService {

    String getName();

    IWidgetDefinition[] getWidgets();
}
