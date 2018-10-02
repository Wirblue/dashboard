package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.service.IService;
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

    public ServiceDto(IService service) {
        name = service.getName();
        widgets = Arrays.stream(service.getWidgets())
                .map(WidgetDto::new)
                .toArray(WidgetDto[]::new);
    }

    private String name;

    private WidgetDto[] widgets;
}
