package com.questionservice.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.questionservice.Entity.Question;
import com.questionservice.questionservice.Entity.QuestionWrapper;
import com.questionservice.questionservice.Entity.Response;
import com.questionservice.questionservice.Service.QuestionService;

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
    
    @PostMapping(path="/generate")
    public ResponseEntity<List<Integer>>generateQuestions
    (@RequestParam int noOfQuestions,@RequestParam String category)
    {
        return questionService.generateQuestions(category,noOfQuestions);
    }
   
    @PostMapping(path="/generateQuiz")
    public ResponseEntity<List<QuestionWrapper>>generateQuiz(@RequestBody List<Integer> questionIds)  {
        
        
        return questionService.generateQuiz(questionIds);
    }

    @PostMapping(path="submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses) {
       
        
        return questionService.submitQuiz(responses);
    
    



}
}
