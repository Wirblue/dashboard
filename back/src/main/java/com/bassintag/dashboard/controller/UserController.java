package com.bassintag.dashboard.controller;

import com.bassintag.dashboard.dto.UserCreationDto;
import com.bassintag.dashboard.dto.UserDto;
import com.bassintag.dashboard.dto.UserRegistrationDto;
import com.bassintag.dashboard.exception.BadRequestException;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserCreationDto register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getPasswordConfirmation())) {
            throw new BadRequestException("Both password must match");
        }
        if (userService.userExists(userRegistrationDto.getUsername())) {
            throw new BadRequestException("This username is already taken");
        }
        return userService.createUser(userRegistrationDto);
    }

    @GetMapping("/me")
    public UserDto me(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return new UserDto(user);
    }

}
