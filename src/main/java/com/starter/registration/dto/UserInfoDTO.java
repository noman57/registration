package com.starter.registration.dto;

import com.starter.registration.enumuration.Breed;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserInfoDTO {

    private String firstName;

    private String lastName;

    private String emailId;

    private String password;

    private Breed preferredPet;
}
