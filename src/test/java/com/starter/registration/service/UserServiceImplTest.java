package com.starter.registration.service;

import com.starter.registration.dto.UserDTO;
import com.starter.registration.entity.User;
import com.starter.registration.repository.UserRepository;
import com.starter.registration.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailId("test24@yahoo.com");
        userDTO.setPassword("testPass");
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
        User savedUser = userService.createUser(userDTO);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getPassword()).isNotEqualTo(userDTO.getPassword());
    }

}
