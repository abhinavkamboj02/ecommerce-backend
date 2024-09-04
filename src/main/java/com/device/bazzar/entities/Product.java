package com.device.bazzar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "Product_Id")
    private String productId;
    @Column(name = "product_Tittle")
    private String tittle;
    @Column(name = "product_Desc", length = 10000)
    private String description;
    @Column(name = "Product_Price")
    private int price;
    @Column(name = "Product_DiscountedPrice")
    private int discountedPrice;
    @Column(name = "product_Quantity")
    private String quantity;
    @Column(name = "Product_Date")
    private Date addedDate;
    @Column(name = "Product_IsLive")
    private boolean live;
    @Column(name = "Product_Stock")
    private boolean stock;
    @Column(name = "Product_ImageName")
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_Id")
    private Category category;


}
