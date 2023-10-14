package com.example.proxyservice.services;

import com.example.proxyservice.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IProductService {
    List<Product> getProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product deleteProduct(Long id);
}