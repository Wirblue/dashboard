package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.widget.IWidgetDefinition;
import lombok.Data;

/**
 * WidgetDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Data
public class WidgetDto {

    public WidgetDto(IWidgetDefinition widget) {
        name = widget.getName();
        description = widget.getDescription();
        params = widget.getParams();
    }

    private String name;

    private String description;

    private ParamDto[] params;
}
