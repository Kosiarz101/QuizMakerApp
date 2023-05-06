package com.QuizMaker.QuizMakerApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Quiz extends JPAEntityBase {

    @Id
    @GeneratedValue
    private Long entityId;

    @Column(nullable = false)
    private String title;

    private boolean isTimeLimited;

    private Long time;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private AppUser owner;
}
