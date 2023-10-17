package com.example.proxyservice.services;

import com.example.proxyservice.dtos.ProductDto;
import com.example.proxyservice.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IProductService {
    List<Product> getProducts();

    Product getProductById(Long id);

    Product createProduct(ProductDto productDto);

    Product updateProduct(Long productId, ProductDto productDto);

    Product replaceProduct(Long productId, ProductDto productDto);

    Product deleteProduct(Long id);
}
