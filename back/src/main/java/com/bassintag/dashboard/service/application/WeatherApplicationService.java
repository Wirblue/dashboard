package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.WeatherAppConfiguration;
import com.bassintag.dashboard.dto.weather.WeatherConditionsDto;
import com.bassintag.dashboard.dto.weather.WeatherDataDto;
import com.bassintag.dashboard.dto.weather.WeatherSysDto;
import com.bassintag.dashboard.dto.weather.WeatherTemperatureDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;

/**
 * MeteoService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Service
@CacheConfig(cacheNames = "WeatherApplication")
public class WeatherApplicationService extends ApplicationService {

    @Resource
    private WeatherApplicationService self;

    private final WeatherAppConfiguration weatherAppConfiguration;

    private RestTemplate restTemplate;

    public WeatherApplicationService(WeatherAppConfiguration weatherAppConfiguration) {
        super("weather");
        this.weatherAppConfiguration = weatherAppConfiguration;
        restTemplate = new RestTemplateBuilder().build();
    }

    public String getIconUrl(String id) {
        return weatherAppConfiguration.getIconUrl() + id + ".png";
    }

    @Cacheable(sync = true)
    public WeatherDataDto getWeatherData(String city) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherAppConfiguration.getWeatherUrl())
                .queryParam("q", city + ",France");
        return invoke(builder);
    }

    public WeatherConditionsDto getWeatherConditions(String city) {
        return self.getWeatherData(city).getWeather()[0];
    }

    public WeatherTemperatureDto getWeatherTemperature(String city) {
        return self.getWeatherData(city).getMain();
    }

    public WeatherSysDto getWeatherSysInfo(String city) {
        return self.getWeatherData(city).getSys();
    }

    private WeatherDataDto invoke(UriComponentsBuilder builder) {
        builder.queryParam("APPID", weatherAppConfiguration.getKey());
        return restTemplate.getForObject(builder.toUriString(), WeatherDataDto.class);
    }
}
