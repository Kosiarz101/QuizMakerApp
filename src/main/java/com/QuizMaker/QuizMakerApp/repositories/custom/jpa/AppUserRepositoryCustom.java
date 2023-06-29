package com.QuizMaker.QuizMakerApp.repositories.custom.jpa;

import com.QuizMaker.QuizMakerApp.models.AppUser;

public interface AppUserRepositoryCustom {
    AppUser add(AppUser entity);
    AppUser update(AppUser entity);
}
