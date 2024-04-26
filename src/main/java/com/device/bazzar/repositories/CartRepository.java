package com.device.bazzar.repositories;

import com.device.bazzar.entities.Cart;
import com.device.bazzar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUser(User user);
}
