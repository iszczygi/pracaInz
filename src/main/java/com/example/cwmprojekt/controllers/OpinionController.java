package com.example.cwmprojekt.controllers;


import com.example.cwmprojekt.classes.Opinion;
import com.example.cwmprojekt.services.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OpinionController {
    @Autowired
    private OpinionService opinionService;

    @PostMapping("/write")
    public String submitOpinion(@ModelAttribute Opinion opinion) {
        // Logic to handle opinion submission would go here
        opinionService.saveOpinion(opinion);
        return "redirect:/home"; // Redirect to home page after submission
    }

    @GetMapping("/write")
    public String showOpinionForm() {
        return "opinion_forms/write"; // Return the opinion_form.html template
    }
}
