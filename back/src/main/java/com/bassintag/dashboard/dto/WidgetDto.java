package com.bassintag.dashboard.dto;

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

    private String name;

    private String description;

    private ParamDto[] params;
}
