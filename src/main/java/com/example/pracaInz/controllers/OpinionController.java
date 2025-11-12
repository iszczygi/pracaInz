package com.example.pracaInz.controllers;


import com.example.pracaInz.classes.Opinion;
import com.example.pracaInz.classes.User;
import com.example.pracaInz.services.OpinionService;
import com.example.pracaInz.services.UserService;
import com.example.pracaInz.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OpinionController {
    @Autowired
    private OpinionService opinionService;

    @Autowired
    private VoteService voteService;
    @Autowired
    private UserService userService;

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
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUserId", user.getId());
        return "opinion_forms/read";
    }

    @PostMapping("/vote")
    public ResponseEntity<?> voteOnOpinion(
            @RequestParam int opinionId,
            @RequestParam int userId,
            @RequestParam int value) {
        voteService.vote(opinionId, userId, value);
        return ResponseEntity.ok().build();
    }


}
