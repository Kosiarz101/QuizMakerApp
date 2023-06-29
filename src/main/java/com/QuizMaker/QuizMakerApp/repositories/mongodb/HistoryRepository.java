package com.QuizMaker.QuizMakerApp.repositories.mongodb;

import com.QuizMaker.QuizMakerApp.models.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends MongoRepository<History, UUID> {
}
