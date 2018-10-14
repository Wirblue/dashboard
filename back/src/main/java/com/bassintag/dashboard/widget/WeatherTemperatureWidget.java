package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamListDto;
import com.bassintag.dashboard.dto.WeatherTemperatureDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.WeatherApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WeatherTemperatureWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Service
public class WeatherTemperatureWidget extends WeatherWidget {

    @Autowired
    public WeatherTemperatureWidget(WeatherApplicationService weatherApplicationService) {
        super(weatherApplicationService, "temperature", "Displays the temperature in a city");
    }

    @Override
    protected WidgetDataDto renderData(User user, ParamListDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        WeatherTemperatureDto weather = getService().getWeatherTemperature(params.getString("city"));
        widgetDataDto.setTitle(String.format("Temperature: %.1f", weather.getTempCelsius()));
        widgetDataDto.setSubtitle(String.format("min: %.1f, max: %.1f", weather.getTempMinCelsius(), weather.getTempMaxCelsius()));
        return widgetDataDto;
    }

}
