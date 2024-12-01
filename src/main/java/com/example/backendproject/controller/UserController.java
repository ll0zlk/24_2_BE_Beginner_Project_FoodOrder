package com.example.backendproject.controller;

import com.example.backendproject.dto.UserDTO;
import com.example.backendproject.entity.User;
import com.example.backendproject.repository.UserRepository;
import com.example.backendproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUpUser(@RequestBody UserDTO userDTO, @RequestParam String password) {
        UserDTO savedUser = userService.signUpUser(userDTO, password);
        return ResponseEntity.ok(savedUser);
    }
}
