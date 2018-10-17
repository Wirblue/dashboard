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

    private long id;

    private long refreshTime;

    private String widgetName;

    private String serviceName;

    private ParamValueDto[] params;

    public WidgetSubscriptionDto() {
    }

    public WidgetSubscriptionDto(WidgetSubscription widgetSubscription) {
        id = widgetSubscription.getId();
        refreshTime = widgetSubscription.getRefreshTime();
        widgetName = widgetSubscription.getWidgetName();
        serviceName = widgetSubscription.getServiceName();
        params = widgetSubscription.getParams().stream().map(ParamValueDto::new).toArray(ParamValueDto[]::new);
    }
}
