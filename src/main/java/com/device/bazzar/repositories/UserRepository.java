package com.device.bazzar.repositories;

import com.device.bazzar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByuserEmail(String userEmail);
    List<User> findByuserNameContaining(String userName);
}
