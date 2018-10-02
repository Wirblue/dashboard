package com.bassintag.dashboard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordService() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String hash(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean compare(String hash, String password) {
        return bCryptPasswordEncoder.matches(password, hash);
    }
}
