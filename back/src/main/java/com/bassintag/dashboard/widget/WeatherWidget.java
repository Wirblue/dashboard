package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.service.application.WeatherApplicationService;

/**
 * WeatherWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
public abstract class WeatherWidget extends WidgetDefinition<WeatherApplicationService> {

    public WeatherWidget(WeatherApplicationService service, String name, String description) {
        super(service, name, description);
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{
                new ParamDto("city", "string")
        };
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
