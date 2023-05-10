package com.QuizMaker.QuizMakerApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Answer extends JPAEntityBase {

    @Id
    @GeneratedValue
    private Long entityId;

    @Column(nullable = false)
    private String answer;

    private boolean isItCorrectAnswer;

    @JsonIgnoreProperties({"answers"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Question question;
}
