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
public class CountryBorderWidget extends WidgetDefinition<CountryApplicationService> {

    @Autowired
    public CountryBorderWidget(CountryApplicationService service) {
        super(service, "borders", "Get all the country borders");
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
        widgetDataDto.setIconImage(countryData.getFlag());
        widgetDataDto.setSubtitle(countryData.getBorders().length + " borders found");
        widgetDataDto.setText(String.join(", ", countryData.getBorders()));
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
