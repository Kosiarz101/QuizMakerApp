package com.QuizMaker.QuizMakerApp.configs;

import com.QuizMaker.QuizMakerApp.enums.PrivilegeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final Environment environment;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

        // jwt
        if (environment.acceptsProfiles(Profiles.of("jwt"))) {
            return http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(
                            authorize ->
                                    authorize.requestMatchers("/user/login").permitAll()
                                            .requestMatchers("/user/register").permitAll()
                                            .requestMatchers("/token").permitAll()
                                            .requestMatchers("/privilege/**").hasAnyAuthority(PrivilegeEnum.PRIVILEGE_MANIPULATION.getValue())
                                            .requestMatchers("/role/**").hasAnyAuthority(PrivilegeEnum.ROLE_MANIPULATION.getValue())
                                            .anyRequest().authenticated()

                    )
                    .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                    .httpBasic(withDefaults())
                    .build();
        } else
        // standard
        {
            return http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(
                            authorize ->
                                    authorize.requestMatchers("/user/login").permitAll()
                                            .requestMatchers("login").permitAll()
                                            .requestMatchers("/user/register").permitAll()
                                            .requestMatchers("/privilege/**").hasAnyAuthority(PrivilegeEnum.PRIVILEGE_MANIPULATION.getValue())
                                            .requestMatchers("/role/**").hasAnyAuthority(PrivilegeEnum.ROLE_MANIPULATION.getValue())
                                            .anyRequest().authenticated()
                    )
                    .httpBasic(withDefaults())
                    .build();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), getDatabaseName()), "test");
//    }
}
