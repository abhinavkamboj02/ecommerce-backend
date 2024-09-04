package com.device.bazzar.entities;

import com.device.bazzar.helper.orderHelper.OrderStatus;
import com.device.bazzar.helper.orderHelper.PaymentStatus;
import com.device.bazzar.helper.orderHelper.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "order_table")
public class Order {
    @Id
    private String orderID;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private int amount;
    private String billingAddress;
    private String phoneNo;
    private String name;
    private Date orderedDate;
    private Date deliveredDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList = new ArrayList<>();
    

}
