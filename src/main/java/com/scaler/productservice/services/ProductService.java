package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product deleteProductById(Long id) throws ProductNotFoundException;

    Product addProduct(Product product);

    void updateProductById();


}
