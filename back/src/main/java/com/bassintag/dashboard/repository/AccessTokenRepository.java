package com.bassintag.dashboard.repository;

import com.bassintag.dashboard.model.AccessToken;
import com.bassintag.dashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * AccessTokenRepository.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 17/10/2018
 */
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    void deleteByUserAndService(User user, String service);

    Optional<AccessToken> findByUserAndService(User user, String service);
}
