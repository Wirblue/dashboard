package com.bassintag.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * WeatherTemperatureDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Data
public class WeatherTemperatureDto {

    private float temp;

    private float pressure;

    private float humidity;

    @JsonProperty("temp_min")
    private float tempMin;

    @JsonProperty("temp_max")
    private float tempMax;

    private static float kelvinToCelsius(float kelvin) {
        return kelvin - 273.15f;
    }

    public float getTempCelsius() {
        return kelvinToCelsius(temp);
    }

    public float getTempMinCelsius() {
        return kelvinToCelsius(tempMin);
    }

    public float getTempMaxCelsius() {
        return kelvinToCelsius(tempMax);
    }
}
