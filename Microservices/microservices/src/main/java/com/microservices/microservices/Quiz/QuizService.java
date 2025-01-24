package com.microservices.microservices.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.microservices.Questions.Question;
import com.microservices.microservices.Questions.QuestionRespository;

@Service
public class QuizService {

    @Autowired
    private QuestionRespository questionRespository;
    @Autowired
    private  QuizRepository quizRepository;
        public ResponseEntity<String> createQuiz(String title, int size, String quizCategory) {
            
            List<Question> questions=questionRespository.createQuiz(quizCategory,size);
            Quiz quiz=new Quiz();
            quiz.setListOfQuestions(questions);
            quiz.setTitle(title);
    
            if(quizRepository.save(quiz)!=null)
            {
                return new ResponseEntity<>("Quiz has been created",HttpStatus.CREATED);
            }
    
            return new ResponseEntity<>("Quiz is not created",HttpStatus.INTERNAL_SERVER_ERROR);
    
    
        }
        public ResponseEntity<List<QuizWrapper>> getQuiz(int id) {
            Optional<Quiz>quizs=quizRepository.findById(id);
            System.out.println(quizs);
            List<QuizWrapper> quizWrappers=new ArrayList<>();
           if(quizs.isPresent())
           {
            List<Question> questions=quizs.get().getListOfQuestions();
            System.out.println(questions);
            
            for(Question question:questions)
            {
                QuizWrapper quizWrapper=new QuizWrapper(question.getId(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4(),question.getQuestionTitle());
                quizWrappers.add(quizWrapper);
            }
           }
           return new ResponseEntity<>(quizWrappers,HttpStatus.OK);


    }
        public ResponseEntity<Integer> validateQuiz(int id, List<Response> responses) {
            Optional<Quiz>quizs=quizRepository.findById(id);
            List<Question> questions=quizs.get().getListOfQuestions();
            int score=0;
            for(int i=0;i<questions.size();i++)
            {
                if(responses.get(i).getResponse().equals(questions.get(i).getAnswer()))
                {
                 System.out.println(responses.get(i).getId());
                    score++;

                }
            }

            return new ResponseEntity<>(score,HttpStatus.OK);
            
            
        }
    
}
