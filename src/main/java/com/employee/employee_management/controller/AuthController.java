package com.employee.employee_management.controller;

import com.employee.employee_management.dto.AuthResponse;
import com.employee.employee_management.entity.User;
import com.employee.employee_management.repository.UserRepository;
import com.employee.employee_management.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password){
        if(userRepository.findByUsername(username).isPresent()){
            return "Username already exists";
        }
        User user = new User();
        user.setUsername(username);
        //Password ko BCryt se encrypt karna
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "User registered successfully";
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestParam String username, @RequestParam String password){
        return authService.login(username, password);
    }
}
