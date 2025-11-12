package com.example.pracaInz.classes;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "opinion")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "opinion")
    private List<Vote> votes;

    private String content;
    private String country;
    private String city;
    private String university;
    private String faculty;
    private String date;

    public int getScore() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

}
