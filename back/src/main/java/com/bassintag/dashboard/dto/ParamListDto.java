package com.bassintag.dashboard.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ParamListDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
@Data
public class ParamListDto {

    @NotNull
    private ParamValueDto[] params;
}
