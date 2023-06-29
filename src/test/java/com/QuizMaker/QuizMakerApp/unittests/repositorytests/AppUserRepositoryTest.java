package com.QuizMaker.QuizMakerApp.unittests.repositorytests;

import com.QuizMaker.QuizMakerApp.enums.RoleEnum;
import com.QuizMaker.QuizMakerApp.models.AppUser;
import com.QuizMaker.QuizMakerApp.models.Role;
import com.QuizMaker.QuizMakerApp.repositories.jpa.AppUserRepository;
import com.QuizMaker.QuizMakerApp.repositories.jpa.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Testcontainers
public class AppUserRepositoryTest {

    @Autowired
    public AppUserRepository appUserRepository;

    @Autowired
    public RoleRepository roleRepository;

    private static Role role;

    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres:14.2-alpine"));

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    public void addTestShouldAddUser() {

        // arrange
        AppUser user = createUser("testEmail@o2.pl", "Username");

        // act
        AppUser userFromDB = appUserRepository.add(user);

        // assert
        assertThat(user.getEntityId()).isNotNull();
        assertThat(user.getEmail()).isEqualTo(userFromDB.getEmail());
    }

    @Test
    public void updateTestShouldUpdateUser() {

        // arrange
        String newUsername = "newUsername";
        String newEmail = "UpdateEmail@o2.pl";
        AppUser user = createUser("testEmail@o2.pl", "Username");
        appUserRepository.add(user);

        // act
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        appUserRepository.update(user);

        // assert
        assertThat(user.getUsername()).isEqualTo(newUsername);
        assertThat(user.getEmail()).isEqualTo(newEmail);
    }

    @BeforeEach
    public void createRole() {
        if (role == null) {
            role = new Role();
            role.setName(RoleEnum.USER);
            roleRepository.save(role);
        }
    }

    @AfterEach
    public void clean() {
        appUserRepository.deleteAll();
    }

    private AppUser createUser(String email, String username) {

        AppUser appUser = new AppUser();
        appUser.setEmail(email);
        appUser.setUsername(username);
        appUser.setRole(role);
        appUser.setPassword("123");

        return appUser;
    }
}
