package com.bassintag.dashboard.dto;

import lombok.Data;

/**
 * ServerDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Data
public class ServerDto {

    private ServiceDto[] services;

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

}
