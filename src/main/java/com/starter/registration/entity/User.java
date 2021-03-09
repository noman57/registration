package com.starter.registration.entity;

import com.starter.registration.enumuration.Breed;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "first name can not be empty")
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = "last name can not be empty")
    private String lastName;

    @Column(nullable = false,unique = true)
    @NotNull(message = "email can not be null!")
    @Email(message = "not a valid email address")
    private String emailId;

    @Column(nullable = false)
    @NotNull(message = "password can not be null!")
    private String password;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime creationTimeStamp = ZonedDateTime.now();

    @Setter(AccessLevel.NONE)
    @Column
    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updateTimeStamp;

    private Breed preferredPet;


}
