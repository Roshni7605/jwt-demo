package com.springpractice.jwt.repository;

import com.springpractice.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * To find user by its email id
     * @param email
     * @return
     */
    Optional<User> findByEmailId(String email);
}
