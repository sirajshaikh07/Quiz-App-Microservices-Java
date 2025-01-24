package com.microservices.microservices.Questions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRespository extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);
@Query(nativeQuery = true, value = "SELECT * FROM question q WHERE q.Category = :cat ORDER BY RAND() LIMIT :size")
List<Question> createQuiz(@Param(value="cat") String category, @Param(value="size") int size);

    
}
