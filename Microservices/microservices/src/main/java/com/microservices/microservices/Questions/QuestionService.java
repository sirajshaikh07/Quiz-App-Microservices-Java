package com.microservices.microservices.Questions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRespository questionRespository;
    public List<Question> getQuestions() {
      
        List<Question>list=questionRespository.findAll();
        return list;
    }
    public List<Question> getQuestionsByCategory(String category) {
        List<Question>list=questionRespository.findByCategory(category);
        return list;

    }
   public Question addQuestion(Question question) {
        try {
            return questionRespository.save(question);
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle the optimistic locking failure exception
            throw new OptimisticLockingFailureException("The question was updated or deleted by another transaction", e);
        }
    }
    

}
