package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.ParamValueDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.model.WidgetSubscription;

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

    WidgetSubscription subscribe(User user, ParamValueDto[] params);
}
