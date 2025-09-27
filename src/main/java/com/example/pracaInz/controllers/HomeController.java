package com.example.pracaInz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
