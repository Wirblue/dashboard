package com.bassintag.dashboard.widget.spotify;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.SpotifyApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NewRelease extends WidgetDefinition<SpotifyApplicationService> {

    public NewRelease(SpotifyApplicationService service) {
        super(service, "top-track", "Shows your most listened track");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[] {
                new ParamDto("country-code", "string")
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widget = new WidgetDataDto();
        CountryCode a = CountryCode.findByName(params.getString("country-code")).get(0);
        AlbumSimplified[] albums = getService().getNews(user, 10, a);
        AlbumSimplified album = albums[(int)(Math.random() * (albums.length - 1))];
        widget.setTitle(album.getArtists()[0].getName() + " - " + album.getName());
        widget.setSubtitle("News in " + params.getString("country-code"));
        widget.setBackgroundImage(album.getImages()[0].getUrl());
        return widget;
    }
}
