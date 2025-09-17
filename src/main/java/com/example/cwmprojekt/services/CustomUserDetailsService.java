package com.example.cwmprojekt.services;

import com.example.cwmprojekt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database by username
        com.example.cwmprojekt.classes.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Create a list with a single authority based on the user's role
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        // Convert the User entity to Spring Security's UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),    // username
                user.getPassword(),    // password (hashed with BCrypt)
                Collections.singletonList(authority)  // single role as authority
        );
    }
}

