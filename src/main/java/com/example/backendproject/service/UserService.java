package com.example.backendproject.service;

import com.example.backendproject.dto.UserDTO;
import com.example.backendproject.entity.User;
import com.example.backendproject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean checkDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO signUpUser(UserDTO userDTO, String password) {
        if (checkDuplicate(userDTO.email())) {
            throw new IllegalArgumentException("Email address already in use");
        }

        User user = new User(
                null,
                userDTO.userName(),
                userDTO.email(),
                userDTO.phone(),
                passwordEncoder.encode(password)
        );

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getUserName(), savedUser.getEmail(), savedUser.getPhone());
    }
}
