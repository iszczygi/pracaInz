package com.example.pracaInz.controllers;

    import com.example.pracaInz.classes.User;
    import com.example.pracaInz.repository.UserRepository;
    import com.example.pracaInz.services.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
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
        private final AuthenticationManager authenticationManager;

        @Autowired
        public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.authenticationManager = authenticationManager;
        }

        @Autowired
        private UserService userService;

        @GetMapping("/register")
        public String showRegistrationForm(Model model) {
            model.addAttribute("user", new User());
            return "user_forms/register";
        }

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

            // Save the user through the service layer
            userService.saveUser(user);

            // Redirect to the home page or another page after login
            return "redirect:/home";
        }

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

            return "user_forms/login";
        }
    }