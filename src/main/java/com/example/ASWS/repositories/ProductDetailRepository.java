package com.example.ASWS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ASWS.models.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

}