package com.bassintag.dashboard.widget.weather;

import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.weather.WeatherConditionsDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.WeatherApplicationService;
import com.bassintag.dashboard.widget.WeatherWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WeatherConditionsWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Service
public class WeatherConditionsWidget extends WeatherWidget {

    @Autowired
    public WeatherConditionsWidget(WeatherApplicationService service) {
        super(service, "conditions", "Displays the weather conditions for a city");
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        String city = params.getString("city");
        WeatherConditionsDto weather = getService().getWeatherConditions(city);
        widgetDataDto.setTitle(city);
        widgetDataDto.setSubtitle(weather.getMain() + " - " + weather.getDescription());
        widgetDataDto.setIconImage(getService().getIconUrl(weather.getIcon()));
        return widgetDataDto;
    }
}
