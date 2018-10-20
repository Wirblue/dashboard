package com.bassintag.dashboard.widget.crypto;

import com.bassintag.dashboard.dto.WidgetSubscriptionParamsDto;
import com.bassintag.dashboard.dto.crypto.CryptoTickerDto;
import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.WidgetDataDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.application.CryptoApplicationService;
import com.bassintag.dashboard.widget.WidgetDefinition;
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
                new ParamDto("symbol1", "string"),
                new ParamDto("symbol2", "string"),
        };
    }

    @Override
    protected WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params) {
        WidgetDataDto widgetDataDto = new WidgetDataDto();
        CryptoTickerDto ticker = getService().getPairPrice(params.getString("symbol1") + params.getString("symbol2"));
        widgetDataDto.setTitle(ticker.getSymbol());
        widgetDataDto.setSubtitle("Exchange rate: " + ticker.getPrice().replaceAll("0+$", ""));
        return widgetDataDto;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }
}
