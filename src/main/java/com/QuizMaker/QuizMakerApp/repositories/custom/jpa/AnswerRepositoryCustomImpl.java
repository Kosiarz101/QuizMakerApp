package com.QuizMaker.QuizMakerApp.repositories.custom.jpa;

import com.QuizMaker.QuizMakerApp.models.Answer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryCustomImpl implements AnswerRepositoryCustom{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Answer add(Answer answer) {
        entityManager.persist(answer);
        return answer;
    }
}
