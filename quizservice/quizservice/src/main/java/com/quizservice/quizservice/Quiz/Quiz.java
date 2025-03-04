package com.quizservice.quizservice.Quiz;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int quizid;
    private String title;
    private String category;
    @ElementCollection
    private List<Integer> questionsIds;


    
}
