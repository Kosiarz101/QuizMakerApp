package com.QuizMaker.QuizMakerApp.services;

import com.QuizMaker.QuizMakerApp.enums.PrivilegeEnum;
import com.QuizMaker.QuizMakerApp.enums.RoleEnum;
import com.QuizMaker.QuizMakerApp.models.AppUser;
import com.QuizMaker.QuizMakerApp.models.Privilege;
import com.QuizMaker.QuizMakerApp.models.Role;
import com.QuizMaker.QuizMakerApp.repositories.jpa.AppUserRepository;
import com.QuizMaker.QuizMakerApp.repositories.jpa.PrivilegeRepository;
import com.QuizMaker.QuizMakerApp.repositories.jpa.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    @Value("${database.initialize-data.enabled}")
    private boolean isEnabled;

    @Value("${admin.account.email}")
    private String adminEmail;

    @Value("${admin.account.password}")
    private String adminPassword;

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isEnabled) {
            Privilege privilege = new Privilege();
            privilege.setName(PrivilegeEnum.PRIVILEGE_MANIPULATION);
            if (!privilegeRepository.existsByName(PrivilegeEnum.PRIVILEGE_MANIPULATION)) {
                privilegeRepository.save(privilege);
            }

            Privilege privilege2 = new Privilege();
            privilege2.setName(PrivilegeEnum.ROLE_MANIPULATION);
            if (!privilegeRepository.existsByName(PrivilegeEnum.ROLE_MANIPULATION)) {
                privilegeRepository.save(privilege2);
            }

            Role role = new Role();
            role.setName(RoleEnum.ADMIN);
            role.getPrivileges().addAll(List.of(privilege, privilege2));
            if (!roleRepository.existsByName(RoleEnum.ADMIN))
                roleRepository.save(role);

            AppUser appUser = new AppUser();
            appUser.setUsername("Admin");
            appUser.setEmail(adminEmail);
            appUser.setPassword(adminPassword);
            appUser.setRole(role);
            if (!appUserRepository.existsByEmail(adminEmail))
                appUserRepository.add(appUser);
        }
    }
}
