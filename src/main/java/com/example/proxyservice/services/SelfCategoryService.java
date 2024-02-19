package com.example.proxyservice.services;

import com.example.proxyservice.models.Product;
import com.example.proxyservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SelfCategoryService implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<String> getCategories() {
        return categoryRepository.findAll().stream().map(category -> category.getName()).toList();
    }

    @Override
    public List<Product> getAllProductsOfSingleCategory(String categoryName) {
        return categoryRepository.findByName(categoryName).getProductList();
    }
}
