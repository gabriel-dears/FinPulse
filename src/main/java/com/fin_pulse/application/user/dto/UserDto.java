package com.fin_pulse.application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDto {

    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

