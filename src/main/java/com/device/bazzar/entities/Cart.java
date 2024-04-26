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
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItems> items  = new ArrayList<>();

}
