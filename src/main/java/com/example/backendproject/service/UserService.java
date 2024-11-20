package com.example.backendproject.service;

import com.example.backendproject.dto.UserDTO;
import com.example.backendproject.entity.User;
import com.example.backendproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUserName(), savedUser.getPhone());
    }
}
