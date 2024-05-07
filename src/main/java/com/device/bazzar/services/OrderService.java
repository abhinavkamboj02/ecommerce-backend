package com.device.bazzar.services;

import com.device.bazzar.dtos.OrderDto;
import com.device.bazzar.entities.User;
import java.util.List;
public interface OrderService {
    //Create order
    OrderDto createOrder(String userId, String cartId, OrderDto orderDto);
    //remove order
    void deleteOrder(String orderId);
    //get order by user
    List<OrderDto>  getByUser(String userId);
    //get all order
    List<OrderDto> getAllOrders();
}
