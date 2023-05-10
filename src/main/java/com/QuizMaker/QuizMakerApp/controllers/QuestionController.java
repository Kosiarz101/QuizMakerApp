package com.QuizMaker.QuizMakerApp.controllers;

import com.QuizMaker.QuizMakerApp.models.Question;
import com.QuizMaker.QuizMakerApp.repositories.jpa.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        return ResponseEntity.status(200).body(questions);
    }

    @PostMapping
    @Transactional
    public ResponseEntity addQuestion(@RequestBody Question question) {
        return ResponseEntity.status(201).body(questionRepository.add(question));
    }
}
