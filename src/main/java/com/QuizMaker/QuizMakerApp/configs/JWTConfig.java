package com.QuizMaker.QuizMakerApp.configs;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@Profile("jwt")
public class JWTConfig {

    @Value("${jwt.key}")
    private String secretKey;

    @Bean
    public JwtDecoder getJwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }

    @Bean
    public JwtEncoder getJwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes(StandardCharsets.UTF_8)));
    }
}
