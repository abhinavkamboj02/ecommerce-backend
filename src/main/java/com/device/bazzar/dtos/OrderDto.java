package com.device.bazzar.dtos;

import com.device.bazzar.entities.OrderItems;
import com.device.bazzar.entities.User;
import com.device.bazzar.helper.orderHelper.OrderStatus;
import com.device.bazzar.helper.orderHelper.PaymentStatus;
import jakarta.persistence.*;
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

public class OrderDto {

    private String orderID;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;
    private int amount;
    private String billingAddress;
    private String phoneNo;
    private String name;
    private Date orderedDate;
    private Date deliveredDate;
    private User user;
    private List<OrderItemsDto> orderItemsList = new ArrayList<>();
}
