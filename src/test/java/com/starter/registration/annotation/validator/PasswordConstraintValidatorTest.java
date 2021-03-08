package com.starter.registration.annotation.validator;

import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.enumuration.Breed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PasswordConstraintValidatorTest {

    UserCreateDTO userCreateDTO;

    static Validator validator;

    @BeforeEach
    public  void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void passwordMustContainUpperCaseChars() {
        createDTO();
        userCreateDTO.setPassword("123@a111");
        Set<ConstraintViolation<UserCreateDTO>> violations = validator.validate(userCreateDTO);
        Iterator<ConstraintViolation<UserCreateDTO>> iterator = violations.iterator();
        assertThat(violations).hasSize(1);
        assertThat(iterator.next().getMessage()).contains("Password must contain 1 or more uppercase characters.");
    }

    @Test
    public void passwordLengthMustBeEight() {
        createDTO();
        userCreateDTO.setPassword("123@7A1");
        Set<ConstraintViolation<UserCreateDTO>> violations = validator.validate(userCreateDTO);
        Iterator<ConstraintViolation<UserCreateDTO>> iterator = violations.iterator();
        assertThat(violations).hasSize(1);
        assertThat(iterator.next().getMessage()).contains("Password must be 8 or more characters in length.");
    }

    @Test
    public void passwordMustContainLowerCaseChars() {
        createDTO();
        userCreateDTO.setPassword("123@aA11");
        Set<ConstraintViolation<UserCreateDTO>> violations = validator.validate(userCreateDTO);
        Iterator<ConstraintViolation<UserCreateDTO>> iterator = violations.iterator();
        assertThat(violations).hasSize(1);
        assertThat(iterator.next().getMessage()).contains("Password must contain 1 or more lowercase characters.");
    }
    private void createDTO() {
        userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmailId("validEmailId@yahoo.com");
        userCreateDTO.setFirstName("fname");
        userCreateDTO.setLastName("lname");
        userCreateDTO.setPreferredPet(Breed.PUG);
    }
}
