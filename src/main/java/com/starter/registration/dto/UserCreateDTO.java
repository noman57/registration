package com.starter.registration.dto;

import com.starter.registration.annotation.ValidPassword;
import com.starter.registration.enumuration.Breed;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateDTO {

    private String firstName;

    private String lastName;

    @Email(message = "Email id  invalid")
    @NotEmpty
    private String emailId;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    private Breed preferredPet;

}
