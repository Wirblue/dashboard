package com.bassintag.dashboard.widget.country;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.country.CountryDataDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.CountryApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryFlagWidget extends WidgetDefinition<CountryApplicationService> {

    @Autowired
    public CountryFlagWidget(CountryApplicationService service) {
        super(service, "flag", "Get country flag");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{
                new ParamDto("country-name", "string")
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        CountryDataDto countryData = getService().getCountryData(params.getString("country-name"))[0];
        widgetDataDto.setTitle(countryData.getName());
        widgetDataDto.setSubtitle("Capital: " + countryData.getCapital());
        widgetDataDto.setBackgroundImage(countryData.getFlag());
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
