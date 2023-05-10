package com.QuizMaker.QuizMakerApp.repositories.custom;

import com.QuizMaker.QuizMakerApp.models.Answer;
import com.QuizMaker.QuizMakerApp.models.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Question add(Question question) {
        for (Answer answer : question.getAnswers()) {
            answer.setQuestion(question);
        }
        entityManager.persist(question);
        return question;
    }
}
