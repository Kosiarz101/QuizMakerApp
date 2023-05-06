package com.QuizMaker.QuizMakerApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class JPAEntityBase {

    @Column(nullable = false, unique = true)
    private UUID id;

    public JPAEntityBase() {
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JPAEntityBase that = (JPAEntityBase) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
