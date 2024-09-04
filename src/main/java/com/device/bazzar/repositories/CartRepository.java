package com.device.bazzar.repositories;

import com.device.bazzar.entities.Cart;
import com.device.bazzar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUser(User user);
}
