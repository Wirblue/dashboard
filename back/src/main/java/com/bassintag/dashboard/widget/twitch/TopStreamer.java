package com.bassintag.dashboard.widget.twitch;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.twitch.GameContainerDto;
import com.bassintag.dashboard.dto.twitch.GameDto;
import com.bassintag.dashboard.dto.twitch.StreamContainerDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.TwitchApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import me.philippheuer.twitch4j.model.Game;
import me.philippheuer.twitch4j.model.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class TopStreamer extends WidgetDefinition<TwitchApplicationService> {

    @Autowired
    public TopStreamer(TwitchApplicationService service) {
        super(service, "top-streamer", "Top streamer for a game");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[] {
                new ParamDto("game", "string"),
                new ParamDto("language", "string")
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        GameContainerDto games = getService().getGames(params.getString("game"));
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        if (games.getGames() == null || games.getGames().length == 0) {
            widgetDataDto.setTitle("N/A");
            widgetDataDto.setSubtitle("No game found");
        } else {
            GameDto game = games.getGames()[0];
            StreamContainerDto streams = getService().getLive(game.getName(), params.getString("language"), 1);
            widgetDataDto.setBackgroundImage(game.getBox().getLarge());
            if (streams.get_total() == 0) {
                widgetDataDto.setTitle("No stream found on " + game.getName());
            } else {
                Stream stream = streams.getStreams()[0];
                widgetDataDto.setTitle(stream.getChannel().getDisplayName() != null ? stream.getChannel().getDisplayName() : stream.getChannel().getName());
                widgetDataDto.setSubtitle("Top " + game.getName() + " streamer with " + stream.getViewers() + "viewers");
                widgetDataDto.setIconImage(stream.getChannel().getLogo());
            }
        }
        return widgetDataDto;
    }
}
