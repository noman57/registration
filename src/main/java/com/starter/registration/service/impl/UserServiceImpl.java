package com.starter.registration.service.impl;

import com.starter.registration.dto.UserDTO;
import com.starter.registration.entity.User;
import com.starter.registration.repository.UserRepository;
import com.starter.registration.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    @Override
    public User createUser(UserDTO userDTO) {
        boolean emailIdTaken = userRepository.existsByEmailId(userDTO.getEmailId());
        if(!emailIdTaken){
            User user = modelMapper.map(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
             return userRepository.save(user);

        }
        return null;
    }
}
