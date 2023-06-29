package com.QuizMaker.QuizMakerApp.repositories.jpa;

import com.QuizMaker.QuizMakerApp.models.AppUser;
import com.QuizMaker.QuizMakerApp.repositories.custom.jpa.AppUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>, AppUserRepositoryCustom {
    Optional<AppUser> findByEmailIgnoreCase(String email);
    Optional<AppUser> findById(UUID id);
    Boolean existsByEmail(String email);
}
