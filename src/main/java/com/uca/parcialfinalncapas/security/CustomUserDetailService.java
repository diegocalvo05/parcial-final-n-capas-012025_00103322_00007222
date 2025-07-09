package com.uca.parcialfinalncapas.security;

import com.uca.parcialfinalncapas.entities.User;
import com.uca.parcialfinalncapas.exceptions.UserNotFoundException;
import com.uca.parcialfinalncapas.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository; // Repository to access user data

    public UserDetails loadUserByUsername(String correo) {
        // Fetches an employee by username or email, throws an exception if not found
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + correo));

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getNombreRol());

        Set<GrantedAuthority> roleSet = new HashSet<GrantedAuthority>();
        roleSet.add(grantedAuthority);

        // Returns a Spring Security User object with the employee's correo, password, and authorities
        return new org.springframework.security.core.userdetails.User(user.getCorreo(), user.getPassword(), roleSet);
    }
}

