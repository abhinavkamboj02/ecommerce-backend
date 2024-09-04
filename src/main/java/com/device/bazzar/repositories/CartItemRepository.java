package com.device.bazzar.repositories;

import com.device.bazzar.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Integer> {
}
