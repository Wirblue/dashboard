package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.service.application.IApplicationService;
import lombok.Data;

import java.awt.*;
import java.util.Arrays;

/**
 * ServiceDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Data
public class ServiceDto {

    public ServiceDto(IApplicationService service) {
        name = service.getName();
        widgets = Arrays.stream(service.getWidgets())
                .map(WidgetDto::new)
                .toArray(WidgetDto[]::new);
        displayColor = new ColorDto(service.getDisplayColor());
    }

    private String name;

    private WidgetDto[] widgets;

    private ColorDto displayColor;
}
