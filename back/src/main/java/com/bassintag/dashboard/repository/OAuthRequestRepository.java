package com.bassintag.dashboard.repository;

import com.bassintag.dashboard.model.OAuthRequest;
import com.bassintag.dashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * OAuthRequestRepository.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
public interface OAuthRequestRepository extends JpaRepository<OAuthRequest, Long> {

    List<OAuthRequest> findAllByUserAndService(User user, String service);
}
