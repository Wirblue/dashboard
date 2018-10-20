package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.CatConfiguration;
import com.bassintag.dashboard.dto.cat.CatImageDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
@CacheConfig(cacheNames = "CatApplication")
public class CatApplicationService extends ApplicationService {

    @Resource
    private CatApplicationService self;

    private final CatConfiguration countryAppConfiguration;

    private RestTemplate restTemplate;

    public CatApplicationService(CatConfiguration countryAppConfiguration) {
        super("cat", new Color( 	241, 240, 236));
        this.countryAppConfiguration = countryAppConfiguration;
        restTemplate = new RestTemplateBuilder().build();
    }

    public CatImageDto[] getCatRandomImage() {
        return restTemplate.getForObject(this.countryAppConfiguration.getCatUrl() + "/images/search?format=json", CatImageDto[].class);
    }
}
