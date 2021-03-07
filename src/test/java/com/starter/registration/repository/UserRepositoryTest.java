package com.starter.registration.repository;

import com.starter.registration.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    void saveShouldSaveWithAudit() {
        User user = createUserObject("test24@yahoo.com");
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getCreationTimeStamp()).isNotNull();
        assertThat(savedUser.getUpdateTimeStamp()).isNotNull();
    }

    @Test
    void emailIdValidationShouldWork() {
        User user = createUserObject("test24");
        Assertions.assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    void emailIdShouldBeUnique() {
        User user = createUserObject("test24@test.com");
        userRepository.save(user);
        User user2 = new User();
        user.setEmailId("test24@test.com");
        user.setPassword("testPass2");
        user.setFirstName("fname");
        user.setLastName("lname");
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(user2);
        });
    }

    @Test
    void existByEmailIdShouldReturn() {
        User user = createUserObject("test24@test.com");
        userRepository.save(user);
        assertThat(userRepository.existsByEmailId(user.getEmailId())).isTrue();
    }

    private User createUserObject(String s) {
        User user = new User();
        user.setEmailId(s);
        user.setPassword("testPass");
        user.setFirstName("fname");
        user.setLastName("lname");
        return user;
    }
}
