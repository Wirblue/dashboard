package com.bassintag.dashboard.dto;

import lombok.Data;

/**
 * WeatherConditionsDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Data
public class WeatherConditionsDto {

    private String main;

    private String description;

    private String icon;
}
