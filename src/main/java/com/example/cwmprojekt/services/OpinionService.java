package com.example.cwmprojekt.services;


import com.example.cwmprojekt.classes.Opinion;
import com.example.cwmprojekt.repository.OpinionRepository;
import com.example.cwmprojekt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;


    @Transactional
    public void saveOpinion(Opinion opinion) {
        opinion.setDate(java.time.LocalDate.now().toString());

        // Retrieve the authenticated user's username
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication().getName();

        // Fetch the User entity from the database
        com.example.cwmprojekt.classes.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the User in the Opinion entity
        opinion.setUser(user);
        Opinion savedOpinion = opinionRepository.save(opinion);
    }
}
