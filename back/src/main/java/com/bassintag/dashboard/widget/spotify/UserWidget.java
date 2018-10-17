package com.bassintag.dashboard.widget.spotify;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.SpotifyApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import org.springframework.stereotype.Service;

/**
 * UserWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Service
public class UserWidget extends WidgetDefinition<SpotifyApplicationService> {

    public UserWidget(SpotifyApplicationService service) {
        super(service, "user", "Shows informations about the current user");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[0];
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widget = new WidgetDataDto();
        com.wrapper.spotify.model_objects.specification.User spotifyUser = getService().getCurrentUser(user);
        widget.setTitle(spotifyUser.getDisplayName());
        widget.setSubtitle("Currently logged in to spotify");
        widget.setIconImage(spotifyUser.getImages()[0].getUrl());
        return widget;
    }
}
