package com.bassintag.dashboard.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * RefreshTimeDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 15/10/2018
 */
@Data
public class RefreshTimeDto {

    @NotNull
    @Min(500)
    private long refreshTime;
}
