package com.bassintag.dashboard.widget.spotify;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.SpotifyApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import com.wrapper.spotify.model_objects.specification.Artist;
import org.springframework.stereotype.Service;

@Service
public class TopArtistWidget extends WidgetDefinition<SpotifyApplicationService> {

    public TopArtistWidget(SpotifyApplicationService service) {
        super(service, "top-artist", "Shows your most listened artist");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[0];
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widget = new WidgetDataDto();
        Artist artist = getService().getTopArtist(user);
        widget.setTitle(artist.getName());
        widget.setSubtitle("Your top artist");
        widget.setBackgroundImage(artist.getImages()[0].getUrl());
        return widget;
    }
}
