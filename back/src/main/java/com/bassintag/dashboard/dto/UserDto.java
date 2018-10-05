package com.bassintag.dashboard.dto;

import com.bassintag.dashboard.model.User;
import lombok.Data;

/**
 * UserDto.java created for dashboard
 *
 * @author Antoine
 * @version 1.0
 * @since 03/10/2018
 */
@Data
public class UserDto {

    public UserDto(User user) {
        setUsername(user.getUsername());
    }

    private String username;
}
