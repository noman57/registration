package com.starter.registration.repository;

import com.starter.registration.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmailId(String emailId);
    Optional<User> findByEmailId(String emailId);
}
