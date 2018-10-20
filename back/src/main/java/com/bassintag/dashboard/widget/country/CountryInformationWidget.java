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
public class CountryInformationWidget extends WidgetDefinition<CountryApplicationService> {
    @Autowired
    public CountryInformationWidget(CountryApplicationService service) {
        super(service, "informations", "Get a lot of informations about the country");
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
        widgetDataDto.setSubtitle("Capital : " + countryData.getCapital());
        widgetDataDto.setIconImage(countryData.getFlag());
        widgetDataDto.setText(
                "native name : " + countryData.getNativeName() + " / " +
                "apha2Code : " + countryData.getAlpha2Code() + " / " +
                        "alpha3Code : " + countryData.getAlpha3Code() + " / " +
                        "region : " + countryData.getRegion() + " / " +
                        "subregion : " + countryData.getSubregion() + " / " +
                        "population : " + countryData.getPopulation() + " / " +
                        "area : " + countryData.getArea() + " / " +
                        "lat : " + countryData.getLatlng()[0] + " / " +
                        "lng : " + countryData.getLatlng()[1]
        );
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
