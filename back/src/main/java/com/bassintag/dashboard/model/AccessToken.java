package com.bassintag.dashboard.model;

import lombok.Data;

import javax.persistence.*;

/**
 * AccessToken.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Data
@Entity
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    private String service;

    private String accessToken;

    private String refreshToken;
}
