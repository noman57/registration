package com.starter.registration.controller;

import com.starter.registration.authentication.UserAuthenticationService;
import com.starter.registration.dto.AuthenticationRequestDTO;
import com.starter.registration.authentication.JwtTokenService;
import com.starter.registration.dto.AuthenticationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserAuthenticationService userDetailsService;

    private final JwtTokenService jwtTokenService;

    private final PasswordEncoder passwordEncoder;


    public AuthenticationController(AuthenticationManager authenticationManager, UserAuthenticationService userDetailsService,
                                    JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
                        passwordEncoder.encode(authenticationRequestDTO.getPassword()), new ArrayList<>())
        );
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequestDTO.getUsername());
        final String jwt = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }
}
