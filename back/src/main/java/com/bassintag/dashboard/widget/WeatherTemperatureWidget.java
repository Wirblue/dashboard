package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.service.application.WeatherApplicationService;

/**
 * WeatherTemperatureWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
public class WeatherTemperatureWidget extends WidgetDefinition<WeatherApplicationService> {

    public WeatherTemperatureWidget(WeatherApplicationService weatherApplicationService) {
        super(weatherApplicationService);
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{
                new ParamDto("city", "string")
        };
    }

    @Override
    public String getName() {
        return "temperature";
    }

    @Override
    public String getDescription() {
        return "Displays the temperature in an area";
    }
}
