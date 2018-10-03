package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.ParamDto;
import com.bassintag.dashboard.dto.ParamValueDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.model.WidgetSubscription;
import com.bassintag.dashboard.service.application.IApplicationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * WidgetDefinition.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
public abstract class WidgetDefinition<T extends IApplicationService> implements IWidgetDefinition {

    private final T service;

    private final ParamDto[] params;

    private final String[] paramsNames;

    public WidgetDefinition(T service) {
        this.service = service;
        params = setupParams();
        paramsNames = Arrays.stream(params).map(ParamDto::getName).toArray(String[]::new);
    }

    protected abstract ParamDto[] setupParams();

    private void validateParam(ParamValueDto value, String type) {
        try {
            switch (type) {
                case "integer":
                    value.asInt();
                    break;
                case "string":
                    value.asString();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public WidgetSubscription subscribe(User user, ParamValueDto[] params) {
        for (ParamDto paramDef : this.params) {
            Optional<ParamValueDto> opt = Arrays.stream(params).filter(p -> p.getName().equals(paramDef.getName())).findFirst();
            if (!opt.isPresent()) {
                throw new BadRequestException("Missing parameter: " + paramDef.getName());
            }
            ParamValueDto value = opt.get();
            validateParam(value, paramDef.getType());
        }
        WidgetSubscription widgetSubscription = new WidgetSubscription();
        widgetSubscription.setUser(user);
        return widgetSubscription;
    }

    @Override
    public ParamDto[] getParams() {
        return params;
    }
}
