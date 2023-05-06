package com.QuizMaker.QuizMakerApp.controllers.authorization;

import com.QuizMaker.QuizMakerApp.services.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@Profile("jwt")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping
    @Transactional
    public String getToken(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }
}
