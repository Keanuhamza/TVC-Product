package com.example.Product.repositories;

import com.example.Product.models.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

}