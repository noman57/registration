package com.starter.registration.annotation.validator;

import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.enumuration.Breed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EnumClassValidatorTest {

    UserCreateDTO userCreateDTO;

    static Validator validator;

    @BeforeEach
    public void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @ParameterizedTest
    @CsvSource({
            "123@a111,Must be a valid choice Breed."

    })
     void validatorShouldReturnExceptionForInvalidInput(String enumValue, String errorMsg) {
        populateUserCreateDTO();
        userCreateDTO.setPreferredPet(enumValue);
        Set<ConstraintViolation<UserCreateDTO>> violations = validator.validate(userCreateDTO);
        Iterator<ConstraintViolation<UserCreateDTO>> iterator = violations.iterator();
        assertThat(violations).hasSize(1);
        assertThat(iterator.next().getMessage()).contains(errorMsg);
    }

    private void populateUserCreateDTO() {
        userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmailId("validEmailId@yahoo.com");
        userCreateDTO.setFirstName("fname");
        userCreateDTO.setLastName("lname");
        userCreateDTO.setPassword("Test-123");
    }
}
