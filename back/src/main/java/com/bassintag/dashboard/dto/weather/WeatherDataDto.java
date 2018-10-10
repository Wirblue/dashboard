package com.bassintag.dashboard.dto.weather;

import lombok.Data;

/**
 * WeatherDataDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Data
public class WeatherDataDto {

    private WeatherConditionsDto[] weather;

    private WeatherTemperatureDto main;

    private WeatherSysDto sys;

}
