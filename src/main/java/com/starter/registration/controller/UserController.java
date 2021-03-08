package com.starter.registration.controller;

import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.dto.UserInfoDTO;
import com.starter.registration.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO,
                                        final UriComponentsBuilder ucBuilder) {
        userService.createUser(userCreateDTO);
        final URI uri = ucBuilder.path("/users/info").buildAndExpand().toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> getUserInfo(HttpServletRequest request) {
        UserInfoDTO userInfoDTO = userService.findUserByEmailId(request.getUserPrincipal().getName());
        return ResponseEntity.ok(userInfoDTO);
    }
}
