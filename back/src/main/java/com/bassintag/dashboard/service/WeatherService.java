package com.bassintag.dashboard.service;

import com.bassintag.dashboard.configuration.WeatherAppConfiguration;
import com.bassintag.dashboard.widget.IWidgetDefinition;
import com.bassintag.dashboard.widget.WeatherTemperatureWidget;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * MeteoService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Service
public class WeatherService implements IService {

    private final WeatherAppConfiguration weatherAppConfiguration;

    private final IWidgetDefinition[] widgets;

    private RestTemplate restTemplate;

    public WeatherService(WeatherAppConfiguration weatherAppConfiguration) {
        this.weatherAppConfiguration = weatherAppConfiguration;
        this.widgets = new IWidgetDefinition[]{
                new WeatherTemperatureWidget()
        };
        restTemplate = new RestTemplateBuilder().build();
    }

    @Cacheable("Weather")
    public String getWeather(String city) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherAppConfiguration.getWeatherUrl())
                .queryParam("q", city, "France");
        return invoke(builder);
    }

    private String invoke(UriComponentsBuilder builder) {
        builder.queryParam("APPID", weatherAppConfiguration.getKey());
        return restTemplate.getForObject(builder.toUriString(), String.class);
    }

    @Override
    public String getName() {
        return "Weather";
    }

    @Override
    public IWidgetDefinition[] getWidgets() {
        return widgets;
    }
}
