package com.example.pracaInz.services;

import com.example.pracaInz.classes.User;
import com.example.pracaInz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional  // This ensures the save operation is done in a transaction
    public User saveUser(User user) {
        if (user.getRole() == null) {
            user.setRole("student");
        }
        User savedUser = userRepository.save(user);
        log.info("User saved with id {} successfully", savedUser.getId());
        return savedUser;
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
