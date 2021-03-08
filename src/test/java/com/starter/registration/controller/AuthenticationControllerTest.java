package com.starter.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.registration.authentication.JwtTokenService;
import com.starter.registration.authentication.UserAuthenticationService;
import com.starter.registration.dto.AuthenticationRequestDTO;
import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.entity.User;
import com.starter.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserAuthenticationService userDetailsService;
    @MockBean
    private JwtTokenService jwtTokenService;

    @Test
     void createShouldReturnOk() throws Exception {
        when(userDetailsService.loadUserByUsername(anyString()))
                .thenReturn(new org.springframework.security.core.userdetails.User("name","email",new ArrayList<>()));
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("username", "password");
        mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(authenticationRequestDTO)
                ))
                .andExpect(status().isOk());
    }


    @Test
     void createShouldReturnNotFound() throws Exception {
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException(""));
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("username", "password");
        mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(authenticationRequestDTO)
                ))
                .andExpect(status().isUnauthorized());
    }
}
