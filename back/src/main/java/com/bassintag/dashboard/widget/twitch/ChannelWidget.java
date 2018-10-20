package com.bassintag.dashboard.widget.twitch;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.TwitchApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import me.philippheuer.twitch4j.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelWidget extends WidgetDefinition<TwitchApplicationService> {

    @Autowired
    public ChannelWidget(TwitchApplicationService service) {
        super(service, "channel", "Displays informations about you channel");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[0];
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        Channel channel = getService().getUserChannel(user);
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        widgetDataDto.setTitle(channel.getStatus());
        widgetDataDto.setSubtitle(channel.getGame());
        widgetDataDto.setIconImage(channel.getLogo());
        return widgetDataDto;
    }
}
