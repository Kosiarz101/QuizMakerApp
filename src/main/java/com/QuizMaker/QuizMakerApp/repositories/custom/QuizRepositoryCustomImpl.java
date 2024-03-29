package com.QuizMaker.QuizMakerApp.repositories.custom;

import com.QuizMaker.QuizMakerApp.models.Quiz;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizRepositoryCustomImpl implements QuizRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Quiz add(Quiz quiz) {
        quiz.setCreationDate(LocalDateTime.now());
        entityManager.persist(quiz);
        return quiz;
    }
}
