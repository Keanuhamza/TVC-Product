package com.example.ASWS.models;
import com.example.ASWS.repositories.*;
import com.example.ASWS.exceptions.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
     
    private final ProductRepository repository;
    private final ProductDetailRepository productDetailRepository;

    ProductService(ProductRepository repository, ProductDetailRepository productDetailRepository) {
        this.repository = repository;
        this.productDetailRepository = productDetailRepository;
    }

    public Product addProduct(Product product) {   
        return repository.save(product);
    }

    public Product getProduct(Long id) {   
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {   
        return repository.findAll();
    }

    public float checkInventory(String productName, int quantity) {
        
        for (Product product : getAllProducts()) {
            if(product.getName().equals(productName)) {
                if (product.getStockQuantity() >= quantity) {
                    return product.getPrice();
                } else {
                    return -1f; 
                }
            }
        }
        return -2f; // dummy value
         
    }

    public String updateProductQuantity(String pName, int quantity) {
        for (Product product : getAllProducts()) {
            if(product.getName().equals(pName)) {
                Long id = product.getId();
                int currentQuantity = product.getStockQuantity();
                product.setStockQuantity(currentQuantity - quantity);

                Product prod = updateProduct(product, id);
                return "Quantity Updated Successfully.";
            }
        }
        return "Error."; // dummy value
    }

    
    public Product updateProduct(Product newProduct, Long id) {
    return repository.findById(id)
      .map(product -> {
        product.setProductCategory(newProduct.getProductCategory());
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setStockQuantity(newProduct.getStockQuantity());
        return repository.save(product);
      })
      .orElseGet(() -> {
        newProduct.setId(id);
        return repository.save(newProduct);
      });
    }

    public Product updateProductProductDetail(Long id, long productDetailid) {
        Product product = repository.findById(id).orElseThrow(RuntimeException::new);
        ProductDetail productDetail = productDetailRepository.findById(productDetailid).orElseThrow(RuntimeException::new);
        product.setProductDetail(productDetail);
        return repository.save(product);
    }

    public void deleteProduct(Long id) {   
        repository.deleteById(id);
    }


}
