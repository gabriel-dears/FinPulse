package com.fin_pulse.infrastructure.user.controller;

import com.fin_pulse.application.user.dto.UserDto;
import com.fin_pulse.domain.user.model.User;
import com.fin_pulse.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody UserDto userDto) {
        // Registration logic here
        return userService.registerUser(userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
    }
}

