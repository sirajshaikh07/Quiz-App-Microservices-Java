package com.quizservice.quizservice.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class QuizService {

    @Autowired
    private QuizInterface quizInterface;
    @Autowired
    private  QuizRepository quizRepository;
        public ResponseEntity<String> createQuiz(String title, int size, String quizCategory) {
            
            Quiz quiz=new Quiz();
            
            quiz.setTitle(title);
            List<Integer> integers=quizInterface.generateQuestions(size, quizCategory).getBody();
            quiz.setCategory(quizCategory);
            quiz.setQuestionsIds(integers);
            quizRepository.save(quiz);
            return new ResponseEntity<>("Quiz is created",HttpStatus.OK);
    
    
        }
        public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
            Optional<Quiz>quizs=quizRepository.findById(id);
            System.out.println(quizs);
            List<QuestionWrapper> quizWrappers=new ArrayList<>();
            List<QuestionWrapper> quList=new ArrayList<>();
           if(quizs.isPresent())
           {
            List<Integer> questionID=quizs.get().getQuestionsIds();
            System.out.println(questionID);
            quList=quizInterface.generateQuiz(questionID).getBody();
            
           }
           return new ResponseEntity<>(quList,HttpStatus.OK);
        }


    // }
        public ResponseEntity<Integer> validateQuiz(int id, List<Response> responses) {
            
            Integer score=quizInterface.submitQuiz(responses).getBody();

            return new ResponseEntity<>(score,HttpStatus.OK);
            
            
    //     }
    
}
}