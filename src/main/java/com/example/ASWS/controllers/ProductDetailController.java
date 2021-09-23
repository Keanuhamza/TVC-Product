package com.example.ASWS.controllers;

import java.util.List;

import com.example.ASWS.models.*;
import com.example.ASWS.services.ProductDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDetailController {

  @Autowired
  private ProductDetailService productDetailService;

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/productdetails")
  List<ProductDetail> all() {
    return productDetailService.getAllProductDetails();
  }
  // end::get-aggregate-root[]

  @PostMapping("/productdetail")
  ProductDetail newProductDetail(@RequestBody ProductDetail newProductDetail) {
    return productDetailService.addProductDetail(newProductDetail);
  }

  // Single item
  @GetMapping("/productdetail/{id}")
  ProductDetail one(@PathVariable Long id) {
    return productDetailService.getProductDetail(id);
  }

  @PutMapping("/productdetail/{id}")
  ProductDetail replaceProductDetail(@RequestBody ProductDetail newProductDetail, @PathVariable Long id) {
    return productDetailService.updateProductDetail(newProductDetail, id);
  }

  @DeleteMapping("/productdetail/{id}")
  void deleteProductDetail(@PathVariable("id") Long id) {
    productDetailService.deleteProductDetail(id);
  }
}
