package com.QuizMaker.QuizMakerApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question extends JPAEntityBase {

    @Id
    @GeneratedValue
    private Long entityId;

    private String question;

    private int points;

    @JsonIgnoreProperties({"question"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;
}
