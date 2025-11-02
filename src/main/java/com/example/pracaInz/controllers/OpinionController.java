package com.example.pracaInz.controllers;


import com.example.pracaInz.classes.Opinion;
import com.example.pracaInz.services.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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


@GetMapping("/read")
public String showReadOpinions(
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String university,
        org.springframework.ui.Model model) {
    List<Opinion> opinions = opinionService.getFilteredOpinions(country, city, university);
    model.addAttribute("opinions", opinions);
    return "opinion_forms/read";
}


}
