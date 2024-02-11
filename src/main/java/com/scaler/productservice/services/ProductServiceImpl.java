package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GenericProductService")
public class ProductServiceImpl implements ProductService{

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public void updateProductById() {

    }
}
