package com.bassintag.dashboard.dto.riotgames;

import lombok.Data;

/**
 * StaticDataWrapper.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 09/10/2018
 */
@Data
public class StaticDataWrapper<T> {

    private String type;

    private String format;

    private String version;

    private T data;
}
