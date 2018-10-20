package com.bassintag.dashboard.widget.cat;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.cat.CatImageDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.CatApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatRandomWidget extends WidgetDefinition<CatApplicationService> {
    @Autowired
    public CatRandomWidget(CatApplicationService service) {
        super(service, "random cat", "Get a random cute cat");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{};
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        CatImageDto countryData = getService().getCatRandomImage()[0];
        widgetDataDto.setBackgroundImage(countryData.getUrl());
        widgetDataDto.setSubtitle(countryData.getUrl());
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
