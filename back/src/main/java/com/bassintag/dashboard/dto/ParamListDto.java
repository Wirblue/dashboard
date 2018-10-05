package com.bassintag.dashboard.dto;

import lombok.Data;

import javax.validation.Valid;
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

    @Valid
    @NotNull
    private ParamValueDto[] params;

    public String getString(String name) {
        for (ParamValueDto param : params) {
            if (param.getName().equals(name))
                return param.getValue();
        }
        throw new IllegalArgumentException("Invalid parameter name: " + name);
    }

    public int getInt(String name) {
        return Integer.parseInt(getString(name));
    }
}
