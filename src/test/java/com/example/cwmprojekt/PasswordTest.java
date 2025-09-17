package com.example.cwmprojekt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "1";  // Raw password you think was used
        String encodedPassword = "$2a$10$ayqZcEEfT5WnrQnEHDz5N.EQruMly5u0/b.8GKzU/ofoqRXz7EGE2";  // Stored hashed password

        boolean matches = encoder.matches(rawPassword, encodedPassword);

        if (matches) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match.");
        }
    }
}
