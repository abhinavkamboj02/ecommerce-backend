package com.device.bazzar.controllers;

import com.device.bazzar.dtos.AddItemToCartRequest;
import com.device.bazzar.dtos.CartDto;
import com.device.bazzar.exception.BadApiRequest;
import com.device.bazzar.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/{userId}")
    ResponseEntity<CartDto> addItemToCart(@PathVariable String userId, @RequestBody AddItemToCartRequest request){
        CartDto cartDto = cartService.addItemToCart(userId, request);
        return new ResponseEntity<>(cartDto, CREATED);
    }
    @PutMapping("/{userId}")
    ResponseEntity<CartDto> removeItemFromCart(@PathVariable String userId, @RequestBody AddItemToCartRequest request){

            CartDto cartDto = cartService.removeItemFromCart(userId, request);
            return new ResponseEntity<>(cartDto, OK);

    }
    @DeleteMapping("/{userId}")
    ResponseEntity<String> clearCart(String userId){
        cartService.clearCart(userId);
        return new ResponseEntity<>("Cart Cleared", OK);
    }


}
