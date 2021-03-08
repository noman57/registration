package com.starter.registration.authentication;

import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenServiceTest {


    private JwtTokenService jwtTokenService = new JwtTokenService();

    @Test
    void extractUsernameShouldReturnUserName() {
        User securityUser = new User("test", "", new ArrayList<>());
        String token = jwtTokenService.generateToken(securityUser);
        assertThat(token).isNotNull();
        String userName = jwtTokenService.extractUsername(token);
        assertThat(userName).isEqualTo("test");
    }

    @Test
    void generateTokenShouldReturnValidToken() {
        User securityUser = new User("test", "", new ArrayList<>());
        String token = jwtTokenService.generateToken(securityUser);
        assertThat(token).isNotNull();
    }

    @Test
    void extractUsernameShouldFailForInvalidToken() {
        Assertions.assertThrows(MalformedJwtException.class, () -> {
            jwtTokenService.extractUsername("invalid token");
        });
    }
}
