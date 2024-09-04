package com.device.bazzar.services;

import com.device.bazzar.dtos.AddItemToCartRequest;
import com.device.bazzar.dtos.CartDto;


public interface CartService {
    //add item to cart
    // if cart not available for user we will create the cart
    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove item from cart
    void removeItemFromCart(String userId, int cartItemId);

    //clear cart
    void clearCart(String userId);
    CartDto getUsercart(String UserId);
}
