package com.bassintag.dashboard.widget.riotgames;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.riotgames.*;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.RiotGamesApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * LastMatchWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Service
public class ChampionMasteryWidget extends WidgetDefinition<RiotGamesApplicationService> {

    @Autowired
    public ChampionMasteryWidget(RiotGamesApplicationService service) {
        super(service, "top-mastery", "Summoner best masteries", 60 * 1000);
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{
                new ParamDto("summoner-name", "string"),
                new ParamDto("rank", "integer")
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        SummonerDto player = getService().summonerByName(params.getString("summoner-name"));
        ChampionMasteryDto[] mastery = getService().championMasteries(player.getId());

        WidgetDataDto widgetDataDto = new WidgetDataDto();

        int rank = params.getInt("rank");
        if (mastery.length == 0 || rank <= 0 || rank > mastery.length) {
            widgetDataDto.setTitle("N/A");
            widgetDataDto.setSubtitle("No mastery to show");
        } else {
            ChampionDto champion = getService().championById(mastery[rank - 1].getChampionId());
            widgetDataDto.setTitle(player.getName() + "'s #" + params.getInt("rank") + " champion is " + champion.getName());
            widgetDataDto.setSubtitle("Mastery " + mastery[rank - 1].getChampionLevel() + " with " + mastery[rank - 1].getChampionPoints() + " points");
            widgetDataDto.setIconImage(getService().profileIcon(player));
            widgetDataDto.setBackgroundImage(getService().championSplash(champion));
        }
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
