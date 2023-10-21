package com.example.proxyservice.services;

import com.example.proxyservice.models.Product;

import java.util.List;

public interface ICategoryService {
    public List<String> getCategories();
    public List<Product> getAllProductsOfSingleCategory(String categoryName);
}
