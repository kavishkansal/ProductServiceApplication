package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String specifiedProductUrl = "https://fakestoreapi.com/products/{id}";
    private String genericProductUrl = "https://fakestoreapi.com/products/";


    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specifiedProductUrl, FakeStoreProductDto.class, id);
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product Not Found");
        }
        return getProductFromFakeStoreProductDto(responseEntity.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        List<Product> productList = new LinkedList<>();
        for (FakeStoreProductDto fakeStoreProductDto : responseEntity.getBody()) {
            productList.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product deleteProductById(Long id) throws ProductNotFoundException {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specifiedProductUrl, FakeStoreProductDto.class,id);
//        restTemplate.delete(specifiedProductUrl,id);

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback =
                restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(specifiedProductUrl, HttpMethod.DELETE, requestCallback,
                        responseExtractor, id);

        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product Not Found to Delete!");
        }
        return getProductFromFakeStoreProductDto(responseEntity.getBody());

    }

    @Override
    public Product addProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(genericProductUrl, getFakeStoreProductDtoFromProduct(product), FakeStoreProductDto.class);
        return getProductFromFakeStoreProductDto(responseEntity.getBody());
    }

    @Override
    public void updateProductById() {

    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDesc(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setDescription(product.getDesc());
        fakeStoreProductDto.setPrice(product.getPrice());
        return fakeStoreProductDto;
    }
}
