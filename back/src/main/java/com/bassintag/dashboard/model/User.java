package com.bassintag.dashboard.model;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    List<WidgetSubscription> widgets;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    List<AccessToken> tokens;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    List<OAuthRequest> oAuthRequests;

}
