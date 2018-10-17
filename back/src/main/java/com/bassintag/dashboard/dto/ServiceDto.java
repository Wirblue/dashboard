package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.service.application.IApplicationService;
import lombok.Data;

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
        if (service.getAuthService() != null)
            authService = service.getAuthService().getName();
    }

    private String name;

    private String authService;

    private WidgetDto[] widgets;
}
