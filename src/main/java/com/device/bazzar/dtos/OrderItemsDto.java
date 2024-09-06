package com.device.bazzar.dtos;

import com.device.bazzar.entities.Order;
import com.device.bazzar.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDto {

    private int orderItemId;
    private int totalPrice;
    private ProductDto product;

}
