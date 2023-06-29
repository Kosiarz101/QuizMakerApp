package com.QuizMaker.QuizMakerApp.repositories.jpa;

import com.QuizMaker.QuizMakerApp.models.Answer;
import com.QuizMaker.QuizMakerApp.repositories.custom.jpa.AnswerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerRepositoryCustom {
}
