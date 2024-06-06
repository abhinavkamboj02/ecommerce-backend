package com.device.bazzar.controllers;

import com.device.bazzar.dtos.OrderDto;
import com.device.bazzar.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/order")
@Tag(name = "Order Controller", description = "REST APIs for Order operations")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/{userId}/{cartId}")
    @Operation(summary = "create order")
    ResponseEntity<OrderDto> createOrder(@PathVariable String userId, @PathVariable String cartId, @RequestBody OrderDto orderDto){
        OrderDto orderDto1 = orderService.createOrder(userId, cartId, orderDto);
        return new ResponseEntity<>(orderDto1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{orderId}")
    @Operation(summary = "delete order")
    ResponseEntity<String> deleteOrder(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
        return  new ResponseEntity<>("successfuly deleted", HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    @Operation(summary = "get order by User")
    ResponseEntity<List<OrderDto>> getByuser(@PathVariable String userId){
        List<OrderDto> orderDtoList = orderService.getByUser(userId);
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "get all User")
    ResponseEntity<List<OrderDto>> getAll(){
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        return new ResponseEntity<>(orderDtoList,HttpStatus.OK);
    }


}
