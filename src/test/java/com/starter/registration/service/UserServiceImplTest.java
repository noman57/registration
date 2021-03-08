package com.starter.registration.service;

import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.dto.UserInfoDTO;
import com.starter.registration.entity.User;
import com.starter.registration.enumuration.Breed;
import com.starter.registration.enumuration.Species;
import com.starter.registration.excetion.ResourceNotFoundException;
import com.starter.registration.repository.UserRepository;
import com.starter.registration.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserService userService;

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @BeforeEach
    void initUseCase() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userService = new UserServiceImpl(userRepository, new BCryptPasswordEncoder(), modelMapper) ;
    }

    @Test
    void createNewUser() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmailId("test24@yahoo.com");
        userCreateDTO.setPassword("testPass");
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
        User savedUser = userService.createUser(userCreateDTO);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getPassword()).isNotEqualTo(userCreateDTO.getPassword());
    }

    @Test
    void findUserByEmailId() {
        User user = new User();
        user.setEmailId("test24@yahoo.com");
        user.setPassword("testPass");
        user.setLastName("Lname");
        user.setFirstName("Fname");
        user.setPreferredPet(Breed.BULL_DOG);
        when(userRepository.findByEmailId("test24@yahoo.com")).thenReturn(Optional.of(user));
        UserInfoDTO userInfoByEmailId = userService.findUserByEmailId(user.getEmailId());
        assertThat(userInfoByEmailId).isNotNull();
        assertThat(userInfoByEmailId.getEmailId()).isEqualTo(user.getEmailId());
        assertThat(userInfoByEmailId.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userInfoByEmailId.getLastName()).isEqualTo(user.getLastName());
        assertThat(userInfoByEmailId.getPreferredPet()).isEqualTo(user.getPreferredPet());
        assertThat(userInfoByEmailId.getPreferredSpecies()).isEqualTo(Species.DOG.name());
    }


    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenNoUserFound() {
        User user = new User();
        user.setEmailId("test24@yahoo.com");
        user.setPassword("testPass");
        user.setLastName("Lname");
        user.setFirstName("Fname");
        when(userRepository.findByEmailId("test24@yahoo.com")).thenThrow(new ResourceNotFoundException(""));
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserByEmailId("test24@yahoo.com");
        });
    }

}
