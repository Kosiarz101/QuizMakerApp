package com.QuizMaker.QuizMakerApp.controllers;

import com.QuizMaker.QuizMakerApp.models.Answer;
import com.QuizMaker.QuizMakerApp.repositories.jpa.AnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerRepository answerRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Answer>> getQuestions() {
        List<Answer> answers = answerRepository.findAll();
        return ResponseEntity.status(200).body(answers);
    }

    @PostMapping
    @Transactional
    public ResponseEntity addQuestion(@RequestBody Answer answer) {
        return ResponseEntity.status(201).body(answerRepository.add(answer));
    }
}
