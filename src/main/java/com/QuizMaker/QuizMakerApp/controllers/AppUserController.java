package com.QuizMaker.QuizMakerApp.controllers;

import com.QuizMaker.QuizMakerApp.models.AppUser;
import com.QuizMaker.QuizMakerApp.repositories.jpa.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserRepository appUserRepository;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getAppUser(@PathVariable("id") UUID id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent())
            return ResponseEntity.status(200).body(appUser.get());
        else
            throw new EntityNotFoundException("User with id " + id + " was not found");
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity createAppUser(@RequestBody AppUser appUser) {
        if (!appUserRepository.existsByEmail(appUser.getEmail())) {
            return ResponseEntity.status(201).body(appUserRepository.add(appUser));
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "User with email " + appUser.getEmail() + " already exists");
        }
    }
}
