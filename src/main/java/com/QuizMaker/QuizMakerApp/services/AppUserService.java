package com.QuizMaker.QuizMakerApp.services;

import com.QuizMaker.QuizMakerApp.models.AppUser;
import com.QuizMaker.QuizMakerApp.repositories.jpa.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmailIgnoreCase(username);
        return appUser.orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
