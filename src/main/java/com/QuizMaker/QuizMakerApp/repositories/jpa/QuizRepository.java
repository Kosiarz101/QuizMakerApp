package com.QuizMaker.QuizMakerApp.repositories.jpa;

import com.QuizMaker.QuizMakerApp.models.Quiz;
import com.QuizMaker.QuizMakerApp.repositories.custom.jpa.QuizRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>, QuizRepositoryCustom {

    @Query(value = "SELECT q.entityId FROM Quiz q WHERE q.id = :id")
    Optional<Long> getEntityIdById(@Param("id") UUID id);
    Optional<Quiz> getQuizById(UUID id);
    Boolean existsById(UUID id);
}
