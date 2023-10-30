package com.example.proxyservice.services;

import com.example.proxyservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);

    Product deleteProduct(Long id);

    List<Product> getAllProductsSortedById();
}
