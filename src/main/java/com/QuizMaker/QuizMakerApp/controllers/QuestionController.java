package com.QuizMaker.QuizMakerApp.controllers;

import com.QuizMaker.QuizMakerApp.models.History;
import com.QuizMaker.QuizMakerApp.models.Question;
import com.QuizMaker.QuizMakerApp.repositories.jpa.QuestionRepository;
import com.QuizMaker.QuizMakerApp.repositories.mongodb.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final HistoryRepository historyRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        return ResponseEntity.status(200).body(questions);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.status(201).body(questionRepository.add(question));
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Question> updateQuestion(@PathVariable(value = "id") UUID id, @RequestBody Question updatedQuestion) {
        Optional<Long> entityId = questionRepository.getEntityIdById(id);
        updatedQuestion.setEntityId(entityId.orElseThrow(
                    () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Resource with id " + id + " was not found"))
                ).setId(id);
        questionRepository.save(updatedQuestion);
        return ResponseEntity.status(200).body(updatedQuestion);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity deleteQuestion(@PathVariable(value = "id") UUID id) {
        Optional<Question> questionOptional = questionRepository.getQuestionById(id);
        Question question = questionOptional.orElseThrow(() -> createNotFoundException(id));
        questionRepository.deleteById(question.getEntityId());
        historyRepository.insert(new History().setId(id).setContent("{type:\"eheh\", content[\"jeje\"]}"));
        return ResponseEntity.status(204).build();
    }

    private ResponseStatusException createNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Resource with id " + id + " was not found");
    }
}
