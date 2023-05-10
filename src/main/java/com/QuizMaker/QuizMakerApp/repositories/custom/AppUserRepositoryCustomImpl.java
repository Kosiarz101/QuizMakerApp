package com.QuizMaker.QuizMakerApp.repositories.custom;

import com.QuizMaker.QuizMakerApp.models.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppUserRepositoryCustomImpl implements AppUserRepositoryCustom{

    @PersistenceContext
    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AppUser add(AppUser entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public AppUser update(AppUser entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entityManager.merge(entity);
        return entity;
    }
}
