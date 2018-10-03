package com.bassintag.dashboard.model;

import lombok.Data;

import javax.persistence.*;

/**
 * WidgetParam.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
@Data
@Entity
public class WidgetParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private WidgetSubscription widget;

    private String key;

    private String value;
}
