package com.microservices.microservices.Quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("quiz")
public class QuizController {

   @Autowired
    private QuizService quizService;
    @PostMapping(path="/createQuiz")
    public ResponseEntity<String> createQuiz(
        @RequestParam String quizCategory,@RequestParam String title,@RequestParam int size  ) {
        
            
        
        return quizService.createQuiz(title,size,quizCategory);
    }
    @GetMapping(path = "/getQuiz/{id}")
    public ResponseEntity<List<QuizWrapper>>getQuiz(@PathVariable int id)
    {
        

        return quizService.getQuiz(id);
    }
    
    @PostMapping(path="/validateQuiz/{id}")
    public ResponseEntity<Integer> validateQuiz(@PathVariable int id,@RequestBody List<Response> responses)
    {
        System.out.println(responses);
        return quizService.validateQuiz(id,responses);
    }
    
}
