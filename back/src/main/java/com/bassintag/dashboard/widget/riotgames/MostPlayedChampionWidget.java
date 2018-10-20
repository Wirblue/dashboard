package com.bassintag.dashboard.widget.riotgames;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.riotgames.*;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.RiotGamesApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import org.springframework.stereotype.Service;

import java.util.Comparator;

/**
 * MostPlayedChampionWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 10/10/2018
 */
@Service
public class MostPlayedChampionWidget extends WidgetDefinition<RiotGamesApplicationService> {

    public MostPlayedChampionWidget(RiotGamesApplicationService service) {
        super(service, "most-played-champion", "Displays the most played champion on the last 20 games", 10 * 60 * 1000);
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{
                new ParamDto("summoner-name", "string")
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        SummonerDto player = getService().summonerByName(params.getString("summoner-name"));
        MatchListDto matchListDto = getService().matchListByAccount(player.getAccountId(), 20);
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        if (matchListDto.getMatches().isEmpty()) {
            widgetDataDto.setTitle("N/A");
            widgetDataDto.setSubtitle("No match information to show");
        } else {
            long mostPlayedId = matchListDto.getMatches().stream().map(MatchReferenceDto::getChampion)
                    .max(Comparator.naturalOrder()).orElseThrow(() -> new RuntimeException("Could not get most played champion"));
            ChampionDto champion = getService().championById(mostPlayedId);
            widgetDataDto.setSubtitle(player.getName() + " most played champion");
            widgetDataDto.setTitle(champion.getName());
            widgetDataDto.setIconImage(getService().profileIcon(player));
            widgetDataDto.setBackgroundImage(getService().championSplash(champion));

        }
        return widgetDataDto;
    }
}
