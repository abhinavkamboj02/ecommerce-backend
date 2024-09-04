package com.device.bazzar.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;
    private int totalPrice;
    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

}
