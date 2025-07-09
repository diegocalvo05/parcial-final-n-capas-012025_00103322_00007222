package com.uca.parcialfinalncapas.controller;

import com.uca.parcialfinalncapas.dto.JwtAuthResponse;
import com.uca.parcialfinalncapas.dto.request.LoginRequest;
import com.uca.parcialfinalncapas.dto.request.UserCreateRequest;
import com.uca.parcialfinalncapas.dto.response.GeneralResponse;
import com.uca.parcialfinalncapas.dto.response.UserResponse;
import com.uca.parcialfinalncapas.service.UserService;
import com.uca.parcialfinalncapas.utils.ResponseBuilderUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<GeneralResponse> createUser(@Valid @RequestBody UserCreateRequest user) {
        UserResponse createdUser = userService.save(user);
        return ResponseBuilderUtil.buildResponse("Usuario creado correctamente", HttpStatus.CREATED, createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest body) {
        String token = userService.login(body);

        JwtAuthResponse res = JwtAuthResponse.builder().accessToken(token).build();

        return ResponseEntity.ok(res);
    }
}
