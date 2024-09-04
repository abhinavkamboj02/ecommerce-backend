package com.device.bazzar.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CartItemsDto {
    private int cartItemId;
    private int quantity;
    private int totalAmount;

    private ProductDto product;

   // private CartDto cart;
}
