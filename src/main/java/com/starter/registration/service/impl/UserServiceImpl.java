package com.starter.registration.service.impl;

import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.dto.UserInfoDTO;
import com.starter.registration.entity.User;
import com.starter.registration.excetion.ResourceNotFoundException;
import com.starter.registration.repository.UserRepository;
import com.starter.registration.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        boolean emailIdTaken = userRepository.existsByEmailId(userCreateDTO.getEmailId());
        if (!emailIdTaken) {
            User user = modelMapper.map(userCreateDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public UserInfoDTO findUserByEmailId(String emailId) {
        Optional<User> userOptional = userRepository.findByEmailId(emailId);
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("Resource not found by emailId " + emailId));
        return modelMapper.map(user, UserInfoDTO.class);
    }



}
