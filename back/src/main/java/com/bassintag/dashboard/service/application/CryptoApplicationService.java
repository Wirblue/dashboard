package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.BinanceConfiguration;
import com.bassintag.dashboard.dto.crypto.CryptoTickerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;

/**
 * CryptoApplicationService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Service
public class CryptoApplicationService extends ApplicationService {

    private RestTemplate restTemplate;

    private final BinanceConfiguration binanceConfiguration;

    @Autowired
    protected CryptoApplicationService(BinanceConfiguration binanceConfiguration) {
        super("crypto", new Color(255, 248, 225));
        this.restTemplate = new RestTemplateBuilder().build();
        this.binanceConfiguration = binanceConfiguration;
    }

    public CryptoTickerDto getPairPrice(String pair) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(binanceConfiguration.getBaseUrl() + "/api/v3/ticker/price")
                .queryParam("symbol", pair);
        return restTemplate.getForObject(builder.toUriString(), CryptoTickerDto.class);
    }

}
