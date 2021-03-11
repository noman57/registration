package com.starter.registration.dto;

import com.starter.registration.annotation.ValidPassword;
import com.starter.registration.annotation.ValueOfEnum;
import com.starter.registration.enumuration.Breed;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email(message = "Email id  invalid")
    @NotEmpty
    private String emailId;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    @ValueOfEnum(enumClass = Breed.class)
    private String preferredPet;

}
