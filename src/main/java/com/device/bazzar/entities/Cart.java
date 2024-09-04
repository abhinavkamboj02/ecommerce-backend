package com.device.bazzar.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {
    @Id
    private String cartId;
    private Date cartedAt;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItems> items =  new ArrayList<>();

}
