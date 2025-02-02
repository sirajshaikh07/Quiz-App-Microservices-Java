package com.questionservice.questionservice.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.questionservice.questionservice.Entity.Question;
import com.questionservice.questionservice.Entity.QuestionWrapper;
import com.questionservice.questionservice.Entity.Response;
import com.questionservice.questionservice.Repo.QuestionRespository;


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
public ResponseEntity<List<Integer>> generateQuestions(String category, int noOfQuestions) {
    
    List<Integer>questions=questionRespository.generateQuestionsForQuiz(category,noOfQuestions);
    return new ResponseEntity<>(questions,HttpStatus.OK);
}
public ResponseEntity<List<QuestionWrapper>> generateQuiz(List<Integer> questionIds) {
   

    List<QuestionWrapper>questionWrappers=new ArrayList<>();
    for(int id:questionIds)
    {
        Question question=questionRespository.findById(id).get();
        QuestionWrapper questionWrapper=new QuestionWrapper(question.getId(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4(),question.getQuestionTitle());
        questionWrappers.add(questionWrapper);
    }
    return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
}
public ResponseEntity<Integer> submitQuiz(List<Response> responses) {
    int score=0;
    for(int i=0;i<responses.size();i++)
    {
        Question question=questionRespository.findById(responses.get(i).getId()).get();
        if(question.getAnswer().equals(responses.get(i).getResponse()))
        {
            score++;
        }

       
    }
     return new ResponseEntity<>(score,HttpStatus.OK);
        

}

   
    

}
