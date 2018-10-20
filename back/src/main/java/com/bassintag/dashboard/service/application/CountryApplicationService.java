package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.CountryConfiguration;
import com.bassintag.dashboard.dto.country.CountryDataDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.awt.*;

/**
 * MeteoService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Service
@CacheConfig(cacheNames = "CountryApplication")
public class CountryApplicationService extends ApplicationService {

    @Resource
    private CountryApplicationService self;

    private final CountryConfiguration countryAppConfiguration;

    private RestTemplate restTemplate;

    public CountryApplicationService(CountryConfiguration countryAppConfiguration) {
        super("country", new Color( 	236, 240, 241));
        this.countryAppConfiguration = countryAppConfiguration;
        restTemplate = new RestTemplateBuilder().build();
    }

    @Cacheable(sync = true)
    public CountryDataDto[] getCountryData(String country) {
        return restTemplate.getForObject(this.countryAppConfiguration.getCountryUrl() + "/name/" + country, CountryDataDto[].class);
    }
}
