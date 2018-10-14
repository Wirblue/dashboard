package com.bassintag.dashboard.dto;

import lombok.Data;

/**
 * RenderedWidgetDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 05/10/2018
 */
@Data
public class RenderedWidgetDto {

    private WidgetDto widget;

    private WidgetDataDto data;
}
