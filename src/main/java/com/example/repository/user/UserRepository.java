package com.example.repository.user;

import com.example.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginAndPassword(String login,String password);
    Optional<User> findByLogin(String login);
}
