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
public class HomeController {

    // Home Page (post-login)
    @GetMapping("/home")
    public String home() {
        return "home";  // Return the home.html template after successful login
    }

    // Index Page
    @GetMapping("/index")
    public String index() {
        return "index"; // Return the index.html template
    }

    @GetMapping("/")
    public String index2() {
        return "index"; // Return the index.html template
    }


}
