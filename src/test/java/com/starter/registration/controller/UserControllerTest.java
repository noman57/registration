package com.starter.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.registration.authentication.JwtTokenService;
import com.starter.registration.authentication.UserAuthenticationService;
import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.dto.UserInfoDTO;
import com.starter.registration.entity.User;
import com.starter.registration.enumuration.Breed;
import com.starter.registration.excetion.ResourceNotFoundException;
import com.starter.registration.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserAuthenticationService userAuthenticationService;

    @MockBean
    private JwtTokenService jwtTokenService;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    @WithMockUser(value = "test@yahoo.com")
     void findByEmailIdShouldReturnOK() throws Exception {
        when(userService.findUserByEmailId(any())).thenReturn(new UserInfoDTO());
        mockMvc.perform(MockMvcRequestBuilders.get("/users/info"))
                .andExpect(status().isOk());
    }

    @Test
     void findByEmailIdShouldThrowException() throws Exception {
        when(userService.findUserByEmailId("test@email.com")).thenThrow(new ResourceNotFoundException(""));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/test@email.com"))
                .andExpect(status().is4xxClientError());
    }


    @Test
     void createShouldReturnOk() throws Exception {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmailId("email@yahoo.com");
        userCreateDTO.setPassword("12345Aa@");
        userCreateDTO.setFirstName("fname");
        userCreateDTO.setLastName("lname");
        userCreateDTO.setPreferredPet(Breed.PUG.toString());
        User user = new User();
        user.setEmailId("test@email.com");
        when(userService.createUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userCreateDTO)
                ))
                .andExpect(status().isCreated());
    }


}
