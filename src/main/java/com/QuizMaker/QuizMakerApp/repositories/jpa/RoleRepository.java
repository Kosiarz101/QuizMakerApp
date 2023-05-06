package com.QuizMaker.QuizMakerApp.repositories.jpa;

import com.QuizMaker.QuizMakerApp.enums.RoleEnum;
import com.QuizMaker.QuizMakerApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> getRoleByName(RoleEnum name);
    Boolean existsByName(RoleEnum name);
}
