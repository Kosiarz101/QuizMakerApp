package com.QuizMaker.QuizMakerApp.models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(value = "history")
@Getter
@Setter
@Accessors(chain = true)
public class History {
    @Id
    UUID id;
    String content;
}
