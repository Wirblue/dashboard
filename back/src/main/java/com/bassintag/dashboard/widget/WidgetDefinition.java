package com.bassintag.dashboard.widget;

import com.bassintag.dashboard.dto.*;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.model.WidgetSubscription;
import com.bassintag.dashboard.service.application.IApplicationService;
import lombok.Getter;
import org.hibernate.annotations.ParamDef;

import java.util.Arrays;
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

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final long   defaultRefreshTime;

    private final ParamDto[] params;

    public WidgetDefinition(T service, String name, String description) {
        this(service, name, description, 10000);
    }

    public WidgetDefinition(T service, String name, String description, long defaultRefreshTime) {
        this.service = service;
        params = setupParams();
        this.name = name;
        this.description = description;
        this.defaultRefreshTime = defaultRefreshTime;
        service.registerWidget(this);
    }

    protected abstract ParamDto[] setupParams();

    private void validateParamType(ParamValueDto value, String type) {
        try {
            switch (type) {
                case "integer":
                    value.asInt();
                    break;
                case "string":
                    value.asString();
                    break;
                case "boolean":
                    value.asBoolean();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public void validateParam(ParamValueDto param) {
        Optional<ParamDto> def = Arrays.stream(this.params).filter(p -> p.getName().equals(param.getName())).findFirst();
        validateParamType(param, def.orElseThrow(() -> new BadRequestException("Invalid Parameter: " + param.getName())).getType());
    }

    @Override
    public WidgetSubscription subscribe(User user, ParamValueDto[] params) {
        for (ParamDto paramDef : this.params) {
            Optional<ParamValueDto> opt = Arrays.stream(params).filter(p -> p.getName().equals(paramDef.getName())).findFirst();
            if (!opt.isPresent()) {
                throw new BadRequestException("Missing parameter: " + paramDef.getName());
            }
        }
        for (ParamValueDto param : params) {
            validateParam(param);
        }
        WidgetSubscription widgetSubscription = new WidgetSubscription();
        widgetSubscription.setUser(user);
        widgetSubscription.setParams(Arrays.stream(params).map(ParamValueDto::toWidgetParam)
                .peek(p -> p.setWidget(widgetSubscription)).collect(Collectors.toList()));
        widgetSubscription.setWidgetName(getName());
        widgetSubscription.setServiceName(getService().getName());
        widgetSubscription.setRefreshTime(getDefaultRefreshTime());
        return widgetSubscription;
    }

    protected abstract WidgetDataDto renderData(User user, WidgetSubscriptionParamsDto params);

    @Override
    public RenderedWidgetDto render(User user, WidgetSubscription subscription) {
        RenderedWidgetDto renderedWidgetDto = new RenderedWidgetDto();
        renderedWidgetDto.setWidget(new WidgetSubscriptionDto(this, subscription));
        WidgetSubscriptionParamsDto paramList = new WidgetSubscriptionParamsDto();
        paramList.setParams(subscription.getParams().stream().map(ParamValueDto::new).toArray(ParamValueDto[]::new));
        renderedWidgetDto.setData(renderData(user, paramList));
        return renderedWidgetDto;
    }

    public T getService() {
        return service;
    }

    @Override
    public ParamDto[] getParams() {
        return params;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean allowsMultiple() {
        return true;
    }

    @Override
    public long getMinimumRefreshTime() {
        return defaultRefreshTime;
    }
}
