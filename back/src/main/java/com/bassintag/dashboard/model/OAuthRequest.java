package com.bassintag.dashboard.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * OAuthRequest.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
@Data
@Entity
public class OAuthRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    private String service;

    private String secret;

    private Date expiry;
}
