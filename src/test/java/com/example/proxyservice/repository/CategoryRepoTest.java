package com.example.proxyservice.repository;

import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoryRepoTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    void saveCategory(){
        //Arrange
        Categories categories = new Categories();
        categories.setName("Test");
        categories.setDescription("Test");

        //Act
        categoryRepository.save(categories);

        //Assert
        assert(categories.getId() != null);
    }

    @Test
    @Transactional
    void saveAndFindCategoryById(){
        //Arrange
        Categories categories = new Categories();
        categories.setName("Test");
        categories.setDescription("Test");
        Categories categories1 = categoryRepository.save(categories);

        long id = categories.getId();

        //Act
        Categories category = categoryRepository.findById(id).get();

        //Assert
        assert(category.getName().equals("Test"));
        assert(category.getDescription().equals("Test"));

    }


    @Test
    @Transactional
    void DeleteCategoryById(){
        //Arrange
        Categories categories = new Categories();
        categories.setName("Test");
        categories.setDescription("Test");
        Categories categories1 = categoryRepository.save(categories);

        long id = categories.getId();

        //Act
        categoryRepository.deleteById(id);

        //Assert
        assert(categoryRepository.findById(id).isEmpty());
    }

    @Test
    @Transactional
    void updateCategoryById(){
        //Arrange
        Categories categories = new Categories();
        categories.setName("Test");
        categories.setDescription("Test");
        Categories categories1 = categoryRepository.save(categories);

        long id = categories.getId();

        //Act
        Categories category = categoryRepository.findById(id).get();
        category.setName("Test1");
        category.setDescription("Test1");
        categoryRepository.save(category);

        //Assert
        assert(category.getName().equals("Test1"));
        assert(category.getDescription().equals("Test1"));
    }

    @Test
    @Transactional
    void findAllProductsOfACategory(){
        //Arrange
        Categories categories = new Categories();
        categories.setName("Test");
        categories.setDescription("Test");
        categoryRepository.save(categories);

        Product product = new Product();
        product.setTitle("Iphone 15 pro");
        product.setCategory(categories);
        product.setDescription("Exclusive Apple product");
        product.setPrice(1250);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        categories.setProductList(productList);

        productRepository.save(product);

        long id = categories.getId();

        //Act
        Categories category = categoryRepository.findById(id).get();

        //Assert
        List<Product> productList2 = category.getProductList();
        assertNotNull(productList2);
        assert(productList2.size() == 1);
    }


}
