package com.sirv.backend.controller;

import com.sirv.backend.EndUserException;
import com.sirv.backend.dto.request.LoginRequest;
import com.sirv.backend.dto.request.RegisterRequest;
import com.sirv.backend.dto.response.LoginResponse;
import com.sirv.backend.model.User;
import com.sirv.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;

    @PostMapping("register")
    public LoginResponse register(@RequestBody RegisterRequest request) {
        try {
            String token = userService.registerUser(request);
            User user = userService.getUserByToken(token).orElseThrow();
            return new LoginResponse(true, null, token, user.getTipo());
        } catch (EndUserException e) {
            return new LoginResponse(false, e.getMessage(), null, null);
        }
    }


    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        try {
            String token = userService.loginUser(request);
            User user = userService.getUserByToken(token).orElseThrow();
            return new LoginResponse(true, null, token, user.getTipo());
        } catch (Exception e) {

            return new LoginResponse(false, e.getMessage(), null, null);
        }
    }
}
