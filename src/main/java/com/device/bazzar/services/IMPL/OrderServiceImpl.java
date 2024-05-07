package com.device.bazzar.services.IMPL;

import com.device.bazzar.dtos.OrderDto;
import com.device.bazzar.entities.*;
import com.device.bazzar.exception.ResourceNotFoundException;
import com.device.bazzar.repositories.CartRepository;
import com.device.bazzar.repositories.OrderRepository;
import com.device.bazzar.repositories.UserRepository;
import com.device.bazzar.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.module.ResolutionException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(String userId, String cartId, OrderDto orderDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No user Found with given Id"));
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("No cart Found with given cart Id"));
        List<CartItems> cartItemsList = cart.getItems();
        if(cartItemsList.size()<1){
            throw new ResourceNotFoundException("invalid cart size");
        }
        AtomicInteger orderAmount = new AtomicInteger(0);
        List<OrderItems> orderItemsList = cartItemsList.stream().map(cartItem -> {
            OrderItems orderItems = OrderItems.builder()
                    .product(cartItem.getProduct())
                    .totalPrice(cartItem.getProduct().getDiscountedPrice()).build();
            orderAmount.addAndGet(orderItems.getTotalPrice());
            return orderItems;
        }).collect(Collectors.toList());

        Order order = Order.builder()
                .orderID(UUID.randomUUID().toString())
                .orderStatus(orderDto.getOrderStatus())
                .paymentStatus(orderDto.getPaymentStatus())
                .billingAddress(orderDto.getBillingAddress())
                .phoneNo(orderDto.getPhoneNo())
                .name(orderDto.getName())
                .orderedDate(new Date())
                .deliveredDate(orderDto.getDeliveredDate())
                .user(orderDto.getUser())
                .orderItemsList(orderItemsList)
                .amount(orderAmount.intValue())
                .build();

        Order savedOrder = orderRepository.save(order);
        OrderDto savedOrderDto = modelMapper.map(savedOrder, OrderDto.class);
        return savedOrderDto;
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResolutionException("No Order Found"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No user Found"));
        List<Order> orderList = orderRepository.findByUser(user);
        List<OrderDto> orderDtoList = orderList.stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
        return orderDtoList;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDto> orderDtoList = orderList.stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
        return orderDtoList;
    }
}
