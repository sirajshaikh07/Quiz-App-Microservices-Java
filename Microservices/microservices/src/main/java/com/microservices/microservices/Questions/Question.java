package com.microservices.microservices.Questions;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity
public class Question {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String category;
private String difficultyLevel;
private String option1;
private String option2;
private String option3;
private String option4;
private String questionTitle;
private String answer;



}
