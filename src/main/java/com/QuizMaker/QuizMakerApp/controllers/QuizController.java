package com.QuizMaker.QuizMakerApp.controllers;

import com.QuizMaker.QuizMakerApp.models.Quiz;
import com.QuizMaker.QuizMakerApp.repositories.jpa.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizRepository quizRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Quiz>> getQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll(Sort.by("creationDate").ascending());
        return ResponseEntity.status(200).body(quizzes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.status(201).body(quizRepository.add(quiz));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateQuiz(@PathVariable("id") UUID id, @RequestBody Quiz newQuiz) {
        Optional<Long> entityIdOptional = quizRepository.getEntityIdById(id);
        Long entityId = entityIdOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Resource with id " + id + " was not found"));
        newQuiz.setEntityId(entityId)
                .setId(id);
        quizRepository.save(newQuiz);
        return ResponseEntity.status(200).body(newQuiz);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteQuiz(@PathVariable("id") UUID id) {
        Optional<Long> entityIdOptional = quizRepository.getEntityIdById(id);
        Long entityId = entityIdOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Resource with id " + id + " was not found"));
        quizRepository.deleteById(entityId);
        return ResponseEntity.status(204).build();
    }
}
