package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.model.WidgetSubscription;
import com.bassintag.dashboard.service.WidgetSubscriptionService;
import com.bassintag.dashboard.widget.IWidgetDefinition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private WidgetDto widgetDefinition;

    private ParamValueDto[] params;

    private String widgetName;

    private String serviceName;

    public WidgetSubscriptionDto() {
    }

    public WidgetSubscriptionDto(WidgetSubscriptionService service, WidgetSubscription widgetSubscription) {
        this(service.getWidgetDefinition(widgetSubscription), widgetSubscription);
    }
    public WidgetSubscriptionDto(IWidgetDefinition widget, WidgetSubscription widgetSubscription) {
        id = widgetSubscription.getId();
        refreshTime = widgetSubscription.getRefreshTime();
        widgetDefinition = new WidgetDto(widget);
        serviceName = widgetSubscription.getServiceName();
        widgetName = widgetSubscription.getWidgetName();
        params = widgetSubscription.getParams().stream().map(ParamValueDto::new).toArray(ParamValueDto[]::new);
    }
}
