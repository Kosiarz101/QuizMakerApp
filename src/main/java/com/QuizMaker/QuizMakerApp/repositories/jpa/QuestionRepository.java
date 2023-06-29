package com.QuizMaker.QuizMakerApp.repositories.jpa;

import com.QuizMaker.QuizMakerApp.models.Question;
import com.QuizMaker.QuizMakerApp.repositories.custom.jpa.QuestionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
    @Query(value = "SELECT q.entityId FROM Question q WHERE q.id = :id")
    Optional<Long> getEntityIdById(@Param("id") UUID id);
    Optional<Question> getQuestionById(UUID id);

}
