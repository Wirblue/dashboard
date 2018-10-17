package com.bassintag.dashboard.service.application;

import com.bassintag.dashboard.configuration.RiotGamesConfiguration;
import com.bassintag.dashboard.dto.riotgames.*;
import com.bassintag.dashboard.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * RiotGamesApplicationService.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Service
@CacheConfig(cacheNames = "RiotGames")
public class RiotGamesApplicationService extends ApplicationService {

    @Resource
    private RiotGamesApplicationService self;

    private final RiotGamesConfiguration riotGamesConfiguration;

    private final RestTemplate restTemplate;

    @Autowired
    public RiotGamesApplicationService(RiotGamesConfiguration riotGamesConfiguration) {
        super("riot");
        this.riotGamesConfiguration = riotGamesConfiguration;
        this.restTemplate = new RestTemplateBuilder().build();
    }

    @Cacheable
    public MatchDto matchById(long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                riotGamesConfiguration.getBaseUrl() + "/lol/match/v3/matches/" + id);
        return invoke(builder, MatchDto.class);
    }

    public MatchListDto matchListByName(String name) {
        SummonerDto summonerDto = self.summonerByName(name);
        return self.matchListByAccount(summonerDto.getAccountId());
    }

    public MatchListDto matchListByAccount(long id, int limit) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                riotGamesConfiguration.getBaseUrl() + "/lol/match/v3/matchlists/by-account/" + id);
        builder.queryParam("endIndex", limit);
        return invoke(builder, MatchListDto.class);
    }


    public MatchListDto matchListByAccount(long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                riotGamesConfiguration.getBaseUrl() + "/lol/match/v3/matchlists/by-account/" + id);
        return invoke(builder, MatchListDto.class);
    }

    public String championIcon(ChampionDto champion) {
        return riotGamesConfiguration.getStaticBaseUrl() + "/cdn/" + self.versions()[0] + "/img/champion/" + champion.getName() + ".png";
    }

    public String championSplash(ChampionDto champion) {
        return riotGamesConfiguration.getStaticBaseUrl() + "/cdn/img/champion/splash/" + champion.getName() + "_0.jpg";
    }

    public String profileIcon(SummonerDto summoner) {
        return riotGamesConfiguration.getStaticBaseUrl() + "/cdn/" + self.versions()[0] + "/img/profileicon/" + summoner.getProfileIconId() + ".png";
    }

    @Cacheable
    public ChampionsStaticDataWrapper champions(String version) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                riotGamesConfiguration.getStaticBaseUrl() + "/cdn/" + version + "/data/en_US/champion.json");
        System.out.println("CHAMPIONS GET: " + builder.toUriString());
        return restTemplate.getForObject(builder.toUriString(), ChampionsStaticDataWrapper.class);
    }

    @Cacheable
    public String[] versions() {
        System.out.println("VERSIONS GET");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                riotGamesConfiguration.getStaticBaseUrl() + "/api/versions.json");
        return restTemplate.getForObject(builder.toUriString(), String[].class);
    }

    @Cacheable
    public SummonerDto summonerByName(String name) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                riotGamesConfiguration.getBaseUrl() + "/lol/summoner/v3/summoners/by-name/" + name);
        return invoke(builder, SummonerDto.class);
    }

    @Cacheable
    public ChampionDto championById(long id) {
        String key = String.valueOf(id);
        Optional<ChampionDto> champion = self.champions(self.versions()[0]).getData().entrySet()
                .stream().map(Map.Entry::getValue).filter(c -> c.getKey().equals(key)).findFirst();
        if (!champion.isPresent()) {
            throw new BadRequestException("Invalid championId: " + id);
        }
        return champion.get();
    }

    private <T> T invoke(UriComponentsBuilder builder, Class<T> clazz) {
        builder.queryParam("api_key", riotGamesConfiguration.getKey());
        System.out.println("REQUESTING: " + builder.toUriString());
        return restTemplate.getForObject(builder.toUriString(), clazz);
    }
}
