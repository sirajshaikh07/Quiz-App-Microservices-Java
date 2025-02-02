package com.quizservice.quizservice.Quiz;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    
     
    @PostMapping(path="question/generate")
    public ResponseEntity<List<Integer>>generateQuestions
    (@RequestParam int noOfQuestions,@RequestParam String category);
    
   
    @PostMapping(path="question/generateQuiz")
    public ResponseEntity<List<QuestionWrapper>>generateQuiz(@RequestBody List<Integer> questionIds);

    @PostMapping(path="question/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses);
}
