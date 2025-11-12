package com.example.pracaInz.services;


import com.example.pracaInz.classes.Opinion;
import com.example.pracaInz.repository.OpinionRepository;
import com.example.pracaInz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Opinion> getAllOpinions() {
        return opinionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Opinion> getFilteredOpinions(String country, String city, String university) {
        if (university != null && !university.isEmpty()) {
            return opinionRepository.findByUniversity(university);
        }
        if (country != null && !country.isEmpty() && city != null && !city.isEmpty()) {
            return opinionRepository.findByCountryAndCity(country, city);
        }
        if (country != null && !country.isEmpty()) {
            return opinionRepository.findByCountry(country);
        }
        if (city != null && !city.isEmpty()) {
            return opinionRepository.findByCity(city);
        }
        return opinionRepository.findAll();
    }

    public Opinion getOpinionById(int id) {
        return opinionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opinion not found"));


    }
}
