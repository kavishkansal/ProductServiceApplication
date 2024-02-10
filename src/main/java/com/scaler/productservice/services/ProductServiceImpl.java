package com.scaler.productservice.services;

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
    public void deleteProductById() {

    }

    @Override
    public void addProduct() {

    }

    @Override
    public void updateProductById() {

    }
}
