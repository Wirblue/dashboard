package com.bassintag.dashboard.widget.spotify;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.SpotifyApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.stereotype.Service;

@Service
public class TopTrackWidget extends WidgetDefinition<SpotifyApplicationService> {

    public TopTrackWidget(SpotifyApplicationService service) {
        super(service, "top-track", "Shows your most listened track");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[0];
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widget = new WidgetDataDto();
        Track track = getService().getTopTrack(user);
        widget.setTitle(track.getArtists()[0].getName() + " - " + track.getName());
        widget.setSubtitle("Your top track");
        widget.setBackgroundImage(track.getAlbum().getImages()[0].getUrl());
        return widget;
    }
}
