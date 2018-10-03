package com.bassintag.dashboard.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * ParamValueDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
@Data
public class ParamValueDto {

    @NotNull
    @Length(min = 1, max = 255)
    private String name;

    @NotNull
    @Length(min = 1, max = 255)
    private String value;

    public String asString() {
        return value;
    }

    public int asInt() {
        return Integer.parseInt(value);
    }
}
