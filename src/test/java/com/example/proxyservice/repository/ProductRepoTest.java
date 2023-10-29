package com.example.proxyservice.repository;

import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        product.setTitle("Iphone 15");
        product.setCategory(categories);
        product.setDescription("Newest Exclusive Apple product");
        product.setPrice(1000);
        productRepository.save(product);

        System.out.println("Debug");
    }

    @Test
    @Transactional
    void saveProductsAndCategory() {
        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories = categoryRepository.save(categories);

        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        product.setCategory(categories);
        productRepository.save(product);

        Categories categories1 = categoryRepository.findById(1L).get();
        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }

    @Test
    @Transactional
    void saveProductAndCategories2(){
        Categories categories = new Categories();
        categories.setName("Fashion");
        categories.setDescription("Fashion");
        categoryRepository.save(categories);

        Product product = new Product();
        product.setTitle("Strapless Dress");
        product.setCategory(categories);
        product.setDescription("Newest Exclusive Zara product");
        product.setPrice(1000);
        productRepository.save(product);

        System.out.println("Debug");
    }


}
