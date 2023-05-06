package com.QuizMaker.QuizMakerApp.controllers.authorization;

import com.QuizMaker.QuizMakerApp.models.Privilege;
import com.QuizMaker.QuizMakerApp.repositories.jpa.PrivilegeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/privilege")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeRepository privilegeRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Privilege>> getPrivileges() {
        List<Privilege> privileges = privilegeRepository.findAll(Sort.by("name").ascending());
        return ResponseEntity.status(200).body(privileges);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Privilege> addPrivilege(@RequestBody Privilege privilege) {
        if (privilegeRepository.existsByName(privilege.getName())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "privilege named " + privilege.getName().getValue() + " already exists");
        }
        return ResponseEntity.status(201).body(privilegeRepository.save(privilege));
    }
}
