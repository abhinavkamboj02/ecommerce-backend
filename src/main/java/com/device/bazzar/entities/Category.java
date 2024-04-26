package com.device.bazzar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_Id")
    private String categoryId;
    @Column(name = "category_tittle")
    private String categoryTittle;
    @Column(name = "category_description")
    private String categoryDescription;
    @Column(name = "ctegory_image")
    private String coverImage;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> listProducts = new ArrayList<>();


}
