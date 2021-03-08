package com.starter.registration.authentication;

import com.starter.registration.entity.User;
import com.starter.registration.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserDetailsService userDetailsService;

    @BeforeEach
    public  void setUp(){
        userDetailsService = new UserAuthenticationService(userRepository);
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setEmailId("emailId");
        user.setFirstName("name");
        user.setPassword("password");
        when(userRepository.findByEmailId("emailId")).thenReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername("emailId");
        assertThat(userDetails).isNotNull();
    }

}
