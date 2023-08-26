package com.sapril.dansmultipro.service;

import org.springframework.stereotype.Service;

import com.sapril.dansmultipro.entity.User;
import com.sapril.dansmultipro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
