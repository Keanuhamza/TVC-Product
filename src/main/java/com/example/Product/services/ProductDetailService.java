package com.example.Product.services;
import java.util.List;

import com.example.Product.exceptions.*;
import com.example.Product.models.ProductDetail;
import com.example.Product.repositories.*;

import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    private ProductDetailRepository repository;

    ProductDetailService(ProductDetailRepository repository) {
        this.repository = repository;
    }

    public ProductDetail addProductDetail(ProductDetail productDetail) {   
        return repository.save(productDetail);
    }

    public ProductDetail getProductDetail(Long id) {   
        return repository.findById(id).orElseThrow(() -> new ProductDetailNotFoundException(id));
    }

    public List<ProductDetail> getAllProductDetails() {   
        return repository.findAll();
    }

    public ProductDetail updateProductDetail(ProductDetail newProductDetail, Long id) {
        return repository.findById(id)
        .map(productDetail -> {
            productDetail.setDescription(newProductDetail.getDescription());
            productDetail.setComment(newProductDetail.getComment());
        return repository.save(productDetail);
        })
        .orElseGet(() -> {
            newProductDetail.setId(id);
            return repository.save(newProductDetail);
        });
    }

    public void deleteProductDetail(Long id) {   
        repository.deleteById(id);
    }
   
}
