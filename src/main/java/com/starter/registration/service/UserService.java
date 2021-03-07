package com.starter.registration.service;

import com.starter.registration.dto.UserDTO;
import com.starter.registration.entity.User;

public interface UserService {

    User createUser(UserDTO userDTO);
}



