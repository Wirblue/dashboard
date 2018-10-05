package com.bassintag.dashboard.repository;

import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.model.WidgetSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * WidgetSubscriptionRepository.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 04/10/2018
 */
public interface WidgetSubscriptionRepository extends JpaRepository<WidgetSubscription, Long> {

    List<WidgetSubscription> getAllByUserAndServiceName(User user, String serviceName);

    WidgetSubscription getByUserAndServiceNameAndWidgetName(User user, String serviceName, String widgetName);
}
