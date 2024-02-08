package com.scaler.productservice.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService {
    @Override
    public String getProductById(Long id) {
        return "Product fetched from fake service. Id: " + id;
    }

    @Override
    public List<String> getAllProducts() {
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
