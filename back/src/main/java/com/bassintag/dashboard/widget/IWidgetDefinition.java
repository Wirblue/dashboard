package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamDto;

/**
 * IWidgetDefinition.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
public interface IWidgetDefinition {

    String getName();

    String getDescription();

    ParamDto[] getParams();
}
