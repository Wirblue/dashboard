package com.bassintag.dashboard.widget.riotgames;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.riotgames.*;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.RiotGamesApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LastMatchWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Service
public class LastMatchWidget extends WidgetDefinition<RiotGamesApplicationService> {

    @Autowired
    public LastMatchWidget(RiotGamesApplicationService service) {
        super(service, "last-match", "Displays info about the last played match");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{
                new ParamDto("summonerName", "string")
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        SummonerDto player = getService().summonerByName(params.getString("summonerName"));
        MatchListDto matchListDto = getService().matchListByAccount(player.getAccountId(), 1);
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        if (matchListDto.getMatches().isEmpty()) {
            widgetDataDto.setTitle("N/A");
            widgetDataDto.setSubtitle("No match information to show");
        } else {
            MatchReferenceDto matchRef = matchListDto.getMatches().get(0);
            MatchDto match = getService().matchById(matchRef.getGameId());
            ParticipantDto participant = match.getParticipantByAccountId(player.getAccountId());
            ParticipantStatsDto stats = participant.getStats();
            widgetDataDto.setSubtitle(player.getName() + " last match");
            ChampionDto champion = getService().championById(matchRef.getChampion());
            widgetDataDto.setTitle(String.format("%s. KDA: %d/%d/%d",
                    (stats.isWin() ? "Won" : "Lost"), stats.getKills(), stats.getDeaths(), stats.getAssists()));
            widgetDataDto.setBackgroundImage(getService().championSplash(champion));
            widgetDataDto.setIconImage(getService().profileIcon(player));
        }
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
