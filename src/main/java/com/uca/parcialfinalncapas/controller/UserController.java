package com.uca.parcialfinalncapas.controller;

import com.uca.parcialfinalncapas.dto.request.UserUpdateRequest;
import com.uca.parcialfinalncapas.dto.response.GeneralResponse;
import com.uca.parcialfinalncapas.dto.response.UserResponse;
import com.uca.parcialfinalncapas.service.UserService;
import com.uca.parcialfinalncapas.utils.ResponseBuilderUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('TECH')")
    public ResponseEntity<GeneralResponse> getAllUsers() {
        List<UserResponse> users = userService.findAll();

        return ResponseBuilderUtil.buildResponse(
                "Usuarios obtenidos correctamente",
                users.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK,
                users
        );
    }

    @GetMapping("/{correo}")
    @PreAuthorize("hasAnyRole('TECH')")
    public ResponseEntity<GeneralResponse> getUserByCorreo(@PathVariable String correo) {
        UserResponse user = userService.findByCorreo(correo);
        return ResponseBuilderUtil.buildResponse("Usuario encontrado", HttpStatus.OK, user);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('USER', 'TECH')")
    public ResponseEntity<GeneralResponse> updateUser(@Valid @RequestBody UserUpdateRequest user) {
        UserResponse updatedUser = userService.update(user);
        return ResponseBuilderUtil.buildResponse("Usuario actualizado correctamente", HttpStatus.OK, updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'TECH')")
    public ResponseEntity<GeneralResponse> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseBuilderUtil.buildResponse("Usuario eliminado correctamente", HttpStatus.OK, null);
    }
}
