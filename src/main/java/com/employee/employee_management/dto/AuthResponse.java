package com.employee.employee_management.dto;

public class AuthResponse {
    private String token;
    private String tokenType;
    private String username;

    public AuthResponse(String token, String tokenType, String username) {
        this.token = token;
        this.tokenType = "Bearer";
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUsername() {
        return username;
    }
}
