package com.bassintag.dashboard.service;

import com.bassintag.dashboard.dto.UserCreationDto;
import com.bassintag.dashboard.dto.UserRegistrationDto;
import com.bassintag.dashboard.model.User;
import com.bassintag.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserCreationDto createUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        userRepository.save(user);
        return new UserCreationDto(user.getUsername());
    }
}
