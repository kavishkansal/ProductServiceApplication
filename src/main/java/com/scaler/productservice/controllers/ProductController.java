package com.scaler.productservice.controllers;

import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<String> getAllProducts() {
        return Collections.emptyList();
    }

//    public String getProductByCategory(String category){
//        return null;
//    }
}
