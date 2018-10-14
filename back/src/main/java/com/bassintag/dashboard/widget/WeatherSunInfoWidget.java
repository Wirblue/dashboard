package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamListDto;
import com.bassintag.dashboard.dto.WeatherConditionsDto;
import com.bassintag.dashboard.dto.WeatherSysDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.WeatherApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WeatherSunInfoWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Service
public class WeatherSunInfoWidget extends WeatherWidget {

    private final SimpleDateFormat dateFormat;

    @Autowired
    public WeatherSunInfoWidget(WeatherApplicationService service) {
        super(service, "sun_info", "Displays sunrise and sunset for a city");
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    @Override
    protected WidgetDataDto renderData(User user, ParamListDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        WeatherSysDto weather = getService().getWeatherSysInfo(params.getString("city"));
        Date sunsetDate = new Date(weather.getSunset() * 1000);
        Date sunriseDate = new Date(weather.getSunrise() * 1000);
        widgetDataDto.setTitle(String.format("Sunrise: %s, Sunset: %s", dateFormat.format(sunriseDate), dateFormat.format(sunsetDate)));
        return widgetDataDto;
    }
}
