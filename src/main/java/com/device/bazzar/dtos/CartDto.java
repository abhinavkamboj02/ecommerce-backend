package com.device.bazzar.dtos;

import com.device.bazzar.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String cartId;
    private Date cartedAt;

    private UserDto user;
    private List<CartItemsDto> items =  new ArrayList<>();
}
