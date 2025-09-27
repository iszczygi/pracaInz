package com.example.pracaInz.services;


import com.example.pracaInz.classes.Opinion;
import com.example.pracaInz.repository.OpinionRepository;
import com.example.pracaInz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        com.example.pracaInz.classes.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the User in the Opinion entity
        opinion.setUser(user);
        Opinion savedOpinion = opinionRepository.save(opinion);
    }
}
