package com.device.bazzar.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDto {
    private String product_Id;
    private String tittle;
    private String description;
    private int price;
    private int discountedPrice;
    private String quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private String imageName;
    private CategoryDto category;
}
