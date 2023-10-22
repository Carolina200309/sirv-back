package com.sirv.backend.controller;

import com.sirv.backend.EndUserException;
import com.sirv.backend.dto.request.LoginRequest;
import com.sirv.backend.dto.request.RegisterRequest;
import com.sirv.backend.dto.response.LoginResponse;
import com.sirv.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("register")
    public LoginResponse register(@RequestBody RegisterRequest request) {
        try {
            String token = userService.registerUser(request);
            return new LoginResponse(true, null, token);
        } catch (EndUserException e) {
            return new LoginResponse(false, e.getMessage(), null);
        }
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        try {
            String token = userService.loginUser(request);
            return new LoginResponse(true, null, token);
        } catch (EndUserException e) {
            return new LoginResponse(false, e.getMessage(), null);
        }
    }
}
