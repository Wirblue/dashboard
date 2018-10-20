package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.ParamValueDto;
import com.bassintag.dashboard.dto.RenderedWidgetDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.model.WidgetSubscription;
import com.bassintag.dashboard.service.application.IApplicationService;

/**
 * IWidgetDefinition.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
public interface IWidgetDefinition {

    String getName();

    IApplicationService getService();

    String getDescription();

    ParamDto[] getParams();

    boolean allowsMultiple();

    void validateParam(ParamValueDto param);

    WidgetSubscription subscribe(User user, ParamValueDto[] params);

    RenderedWidgetDto render(User user, WidgetSubscription subscription);

    long getMinimumRefreshTime();
}
