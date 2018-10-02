package com.bassintag.dashboard.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {

    @NotNull
    @Size(min = 2, max = 255)
    private String username;

    @NotNull
    @Size(min = 2, max = 255)
    private String password;

    @NotNull
    @Size(min = 2, max = 255)
    private String passwordValidation;
}
