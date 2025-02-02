package com.questionservice.questionservice.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.questionservice.questionservice.Entity.Question;

@Repository
public interface QuestionRespository extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);
// @Query(nativeQuery = true, value = "SELECT * FROM question q WHERE q.Category = :cat ORDER BY RAND() LIMIT :size")
// List<Question> createQuiz(@Param(value="cat") String category, @Param(value="size") int size);
@Query(nativeQuery = true, value = "SELECT q.id FROM question q WHERE q.Category = :category ORDER BY RAND() LIMIT :noOfQuestions")
List<Integer> generateQuestionsForQuiz(String category, int noOfQuestions);

    
}
