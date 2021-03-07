package com.starter.registration.dto;

import com.starter.registration.annotation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDTO {

    private String firstName;

    private String lastName;

    @Email(message = "Email id  invalid")
    @NotEmpty
    private String emailId;

    @NotEmpty
    @ValidPassword
    private String password;

}
