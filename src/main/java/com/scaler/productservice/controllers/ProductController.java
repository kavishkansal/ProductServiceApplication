package com.scaler.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Product fetched with id: " + id;
    }

    @GetMapping()
    public List<String> getAllProducts() {
        return Collections.emptyList();
    }

//    public String getProductByCategory(String category){
//        return null;
//    }
}
