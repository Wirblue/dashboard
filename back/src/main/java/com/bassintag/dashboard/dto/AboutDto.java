package com.bassintag.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AboutDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 01/10/2018
 */
@Data
@AllArgsConstructor
public class AboutDto {

    private ClientDto client;

    private ServerDto server;
}
