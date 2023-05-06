package com.QuizMaker.QuizMakerApp.repositories.jpa;

import com.QuizMaker.QuizMakerApp.enums.PrivilegeEnum;
import com.QuizMaker.QuizMakerApp.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> getPrivilegeByName(PrivilegeEnum name);
    Boolean existsByName(PrivilegeEnum name);
}
