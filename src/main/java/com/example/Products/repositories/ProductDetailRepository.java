package com.example.Products.repositories;

import com.example.Products.models.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

}