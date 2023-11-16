package com.example.proxyservice.repository;

import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductRepoTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Transactional
    void saveProductAndCategories(){
        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categoryRepository.save(categories);

        Product product = new Product();
        product.setTitle("Iphone");
        product.setCategory(categories);
        product.setDescription("Exclusive Apple product");
        product.setPrice(1000);
        productRepository.save(product);
    }

    @Test
    @Transactional
    void savenullProduct(){
        Product product =null;

        assertThrows(InvalidDataAccessApiUsageException.class,()->productRepository.save(product));


    }


}
