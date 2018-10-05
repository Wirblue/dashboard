package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.model.WidgetSubscription;
import lombok.Data;

/**
 * WidgetSubscriptionDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 04/10/2018
 */
@Data
public class WidgetSubscriptionDto {

    private String widgetName;

    private String serviceName;

    private ParamValueDto[] params;

    public WidgetSubscriptionDto(WidgetSubscription widgetSubscription) {
        widgetName = widgetSubscription.getWidgetName();
        serviceName = widgetSubscription.getServiceName();
        params = widgetSubscription.getParams().stream().map(ParamValueDto::new).toArray(ParamValueDto[]::new);
    }
}
