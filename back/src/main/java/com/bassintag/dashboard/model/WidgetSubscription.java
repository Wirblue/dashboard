package com.bassintag.dashboard.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * WidgetSubscription.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 02/10/2018
 */
@Data
@Entity
public class WidgetSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String serviceName;

    private String widgetName;

    private long refreshTime = 10000;

    @ManyToOne
    private User user;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "widget")
    private List<WidgetParam> params;

}
