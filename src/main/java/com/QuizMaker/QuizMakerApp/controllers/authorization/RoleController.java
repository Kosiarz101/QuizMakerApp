package com.QuizMaker.QuizMakerApp.controllers.authorization;

import com.QuizMaker.QuizMakerApp.models.Role;
import com.QuizMaker.QuizMakerApp.repositories.jpa.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleRepository.findAll(Sort.by("name").ascending());
        return ResponseEntity.status(200).body(roles);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Role " + role.getName().getValue() + " already exists");
        }
        return ResponseEntity.status(201).body(roleRepository.save(role));
    }
}
