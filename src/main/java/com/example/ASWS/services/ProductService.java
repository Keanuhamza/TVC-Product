package com.example.ASWS.services;
import com.example.ASWS.repositories.*;
import com.example.ASWS.exceptions.*;
import com.example.ASWS.models.Product;
import com.example.ASWS.models.ProductDetail;

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

    public String addProduct(Product product) { 
    int totalOccurances = 0; // there should only be 1 product with the name inputted above.
      for (Product p : getAllProducts()) {
          if (p.getName().equals(product.getName())) {
              totalOccurances++;
          }
      }
      if (totalOccurances > 0) {
          try {
            throw new ProductNameDuplicateException(product.getName());
          } catch (Exception e) {
            e.printStackTrace();
          }
      } else {
        repository.save(product);
        return product.toString();
      } 

      return "\nError, product name already exists: " + product.getName();
    }

    public Product getProduct(Long id) {   
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product getProductByName(String productName) { 
      for (Product product : getAllProducts()) {
        if(product.getName().equals(productName)) {
            return product;
        } else {
            throw new ProductNotFoundException(productName);
          }
      } 
      return null; 
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

                updateProduct(product, id);
                return "Quantity Updated Successfully.";
            }
        }
        return "Error."; // dummy value, an error is not possible in this contexts.
    }

    
    public String updateProduct(Product newProduct, Long id) {
    Product prod = repository.findById(id)
      .map(product -> {
        product.setProductCategory(newProduct.getProductCategory());
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setStockQuantity(newProduct.getStockQuantity());
        return product;
      })
      .orElseGet(() -> {
        newProduct.setId(id);
        return newProduct;
      });

      int totalOccurances = 0; // there should only be 1 product with the name inputted above.
      for (Product p : getAllProducts()) {
          if (p.getName().equals(prod.getName())) {
              totalOccurances++;
          }
      }
      if (totalOccurances > 1) {
        try {
            throw new ProductNameDuplicateException(prod.getName());
          } catch (Exception e) {
            e.printStackTrace();
          }
      } else {
        repository.save(prod);
        return prod.toString();
      }

      return "\nError, product name already exists: " + prod.getName();
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
