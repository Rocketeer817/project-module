package com.example.proxyservice.services;

import com.example.proxyservice.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Override
    public List<Product> getProducts() {
        return new ArrayList<Product>();
    }

    @Override
    public Product getProductById(Long id) {
        return new Product();
    }

    @Override
    public Product createProduct(Product product) {
        return new Product();
    }

    @Override
    public Product updateProduct(Product product) {
        return new Product();
    }

    @Override
    public Product deleteProduct(Long id) {
        return new Product();
    }
}
