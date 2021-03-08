package com.starter.registration.controller;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private  AuthenticationManager authenticationManager= Mockito.mock(AuthenticationManager.class);
    private  UserAuthenticationService userDetailsService= Mockito.mock(UserAuthenticationService.class);

    @Test
    public void createShouldReturnOk() throws Exception {
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("username","password");
        mockMvc.perform(MockMvcRequestBuilders.get("/authenticate"))
                .andExpect(status().isOk());
    }

}
