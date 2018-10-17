package com.bassintag.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthRedirectionDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRedirectionDto {

    private String externalService;

    private String redirectionUri;

    private String username;

    private String secret;
}
