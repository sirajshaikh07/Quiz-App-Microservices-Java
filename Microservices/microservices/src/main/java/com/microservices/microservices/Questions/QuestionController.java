package com.microservices.microservices.Questions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path="question")
public class QuestionController{
    

    @Autowired
    private QuestionService questionService;

    @GetMapping(path="/questions")
    public List<Question>getQuestions()
    {
       
        return questionService.getQuestions();

    }

    @GetMapping(path="/questions/{category}")
    public List<Question>getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }
   

    @PostMapping(path="/questions")
    public Question postMethodName(@RequestBody Question question) {
        //TODO: process POST request
        
        return questionService.addQuestion(question);
    }
    
   



}
