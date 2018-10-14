package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.CryptoTickerDto;
import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.ParamListDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.CryptoApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CryptoTickerWidget.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Service
public class CryptoTickerWidget extends WidgetDefinition<CryptoApplicationService> {

    @Autowired
    public CryptoTickerWidget(CryptoApplicationService service) {
        super(service, "ticker", "Displays the price of a crypto pair");
    }

    @Override
    protected ParamDto[] setupParams() {
        return new ParamDto[]{
                new ParamDto("pair", "string")
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, ParamListDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        CryptoTickerDto ticker = getService().getPairPrice(params.getString("pair"));
        widgetDataDto.setTitle(ticker.getSymbol() + ": " + ticker.getPrice());
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
