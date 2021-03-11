package com.starter.registration.initializer;

import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.enumuration.Breed;
import com.starter.registration.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setLastName("lname");
        userCreateDTO.setFirstName("fname");
        userCreateDTO.setEmailId("test@test.com");
        userCreateDTO.setPassword("password");
        userCreateDTO.setPreferredPet(Breed.PUG.getBreed());
        userService.createUser(userCreateDTO);
    }
}
