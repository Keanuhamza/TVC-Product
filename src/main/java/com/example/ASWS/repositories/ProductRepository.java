package com.example.ASWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ASWS.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}