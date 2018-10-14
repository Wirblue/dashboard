package com.bassintag.dashboard.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * BinanceConfiguration.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Data
@Configuration
public class BinanceConfiguration {

    @Value("${app.binance.base-url}")
    private String baseUrl;
}
