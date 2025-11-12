package com.example.pracaInz.services;

import com.example.pracaInz.classes.Opinion;
import com.example.pracaInz.classes.User;
import com.example.pracaInz.classes.Vote;
import com.example.pracaInz.repository.OpinionRepository;
import com.example.pracaInz.repository.UserRepository;
import com.example.pracaInz.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {

    private final VoteRepository voteRepository;
    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;

    public void vote(int opinionId, int userId, int value) {
        Opinion opinion = opinionRepository.findById(opinionId)
                .orElseThrow(() -> new RuntimeException("Opinion not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Vote> existingVote = voteRepository.findByOpinionAndUser(opinion, user);
        if (existingVote.isPresent()) {
            Vote vote = existingVote.get();
            vote.setValue(value);
            voteRepository.save(vote);
        } else {
            Vote newVote = new Vote();
            newVote.setOpinion(opinion);
            newVote.setUser(user);
            newVote.setValue(value);
            voteRepository.save(newVote);
        }
    }
}