package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamListDto;
import com.bassintag.dashboard.dto.WeatherConditionsDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.WeatherApplicationService;
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
    protected WidgetDataDto renderData(User user, ParamListDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        WeatherConditionsDto weather = getService().getWeatherConditions(params.getString("city"));
        widgetDataDto.setTitle(weather.getMain());
        widgetDataDto.setSubtitle(weather.getDescription());
        widgetDataDto.setBackgroundImage(getService().getIconUrl(weather.getIcon()));
        return widgetDataDto;
    }
}
