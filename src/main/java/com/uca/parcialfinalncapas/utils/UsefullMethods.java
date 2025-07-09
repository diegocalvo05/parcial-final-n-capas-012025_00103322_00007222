package com.uca.parcialfinalncapas.utils;


import com.uca.parcialfinalncapas.entities.User;
import com.uca.parcialfinalncapas.repository.UserRepository;
import com.uca.parcialfinalncapas.security.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UsefullMethods {
    private final JwtProvider tokenProvider;
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    // gets the information of the current logged in user and the requested user in params
    public CurrentUserInfo getUserInfo(Long userId) {
        String token = tokenProvider.getUserToken(request);
        Long idFromToken = tokenProvider.getIdFromToken(token);

        List<String> roles = tokenProvider.getRolesFromToken(token);
        User currentUser = userRepository.findById(idFromToken).orElse(null);
        User requestedUser = userRepository.findById(userId).orElse(null);

        return new CurrentUserInfo(
                roles,
                currentUser,
                requestedUser
        );
    }
}
