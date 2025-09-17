package com.example.cwmprojekt.controllers;

import com.example.cwmprojekt.classes.User;
import com.example.cwmprojekt.repository.UserRepository;
import com.example.cwmprojekt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registration Page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Create a new User object for the form
        return "user_forms/register"; // Look for register.html in the templates folder
    }

    // Handle Registration Form Submission
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email is already registered");
            return "user_forms/register";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username is already taken");
            return "user_forms/register";
        }

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user through the service layer
        userService.saveUser(user);

        model.addAttribute("message", "User registered successfully");
        return "redirect:/user_forms/login";
    }


    // Login Page
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "user_forms/login";  // Return the login.html template
    }


}
