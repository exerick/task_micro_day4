package com.day4.spring.security.task1.repository;

import com.day4.spring.security.task1.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByUsername(String username);
    boolean existsByUsername(String username);

}
