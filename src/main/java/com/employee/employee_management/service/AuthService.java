package com.employee.employee_management.service;

import com.employee.employee_management.dto.AuthResponse;
import com.employee.employee_management.entity.User;
import com.employee.employee_management.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse login(String username, String password){
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null){
           throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }
        String token =  jwtService.generateToken(user.getUsername());
        return new AuthResponse(token, "Bearer", user.getUsername());
    }
}
