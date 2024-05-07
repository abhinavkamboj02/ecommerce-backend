package com.device.bazzar.dtos;

import com.device.bazzar.entities.Order;
import com.device.bazzar.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class OrderItemsDto {
    private int orderItemId;
    private int totalPrice;
    private Product product;
    private Order order;
}
