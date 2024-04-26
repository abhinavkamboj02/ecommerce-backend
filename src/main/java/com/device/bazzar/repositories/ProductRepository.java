package com.device.bazzar.repositories;

import com.device.bazzar.entities.Category;
import com.device.bazzar.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByTittleContaining(String subTittle);
    List<Product> findByLiveTrue();
    List<Product> findByCategory(Category category);


}
