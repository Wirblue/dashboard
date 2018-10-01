package com.bassintag.dashboard.dto;

import lombok.Data;

/**
 * ServiceDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Data
public class ServiceDto {

    private String name;

    private WidgetDto[] widgets;
}
