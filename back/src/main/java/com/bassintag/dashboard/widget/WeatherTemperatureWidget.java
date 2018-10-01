package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamDto;

/**
 * WeatherTemperatureWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
public class WeatherTemperatureWidget implements IWidgetDefinition {

    private final ParamDto[] params;

    public WeatherTemperatureWidget() {
        params = new ParamDto[]{
                new ParamDto("city", "string")
        };
    }

    @Override
    public String getName() {
        return "Temperature";
    }

    @Override
    public String getDescription() {
        return "Displays the temperature in an area";
    }

    @Override
    public ParamDto[] getParams() {
        return params;
    }
}
