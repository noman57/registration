package com.starter.registration.repository;

import com.starter.registration.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmailId(String emailId);
    User findByEmailId(String emailId);
}
