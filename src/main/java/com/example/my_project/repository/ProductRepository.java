package com.example.my_project.repository;


import com.example.my_project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product>findByBrand(String brand);

    boolean existsByBrand(String brand);
}
