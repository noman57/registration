package com.starter.registration.authentication;

import com.starter.registration.repository.UserRepository;
import com.starter.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private UserRepository userRepository;

    public UserAuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) {
        Optional<com.starter.registration.entity.User> userOptional = userRepository.findByEmailId(emailId);
        com.starter.registration.entity.User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user found with email Id " + emailId));
        return new User(user.getEmailId(), user.getPassword(), new ArrayList<>());
    }
}
