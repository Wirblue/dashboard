package com.bassintag.dashboard.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * WeatherAppConfiguration.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Data
@Configuration
@ConfigurationProperties("app.country")
public class CountryConfiguration {

    @Value("${app.country.base-url}")
    private String countryUrl;

}
