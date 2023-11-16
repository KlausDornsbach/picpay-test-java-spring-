package com.picpayDemo.picpayDemo.Repos;

import com.picpayDemo.picpayDemo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByDocument(String document);
}
