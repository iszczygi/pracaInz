package com.example.pracaInz.repository;

import com.example.pracaInz.classes.Opinion;
import com.example.pracaInz.classes.User;
import com.example.pracaInz.classes.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
@Query("SELECT v FROM Vote v WHERE v.opinion = :opinion AND v.user = :user")
Optional<Vote> findByOpinionAndUser(@Param("opinion") Opinion opinion, @Param("user") User user);
}
