package com.starter.registration.service;

import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.dto.UserInfoDTO;
import com.starter.registration.entity.User;

public interface UserService {

    User createUser(UserCreateDTO userCreateDTO);

    UserInfoDTO findUserByEmailId(String emailId);
}



