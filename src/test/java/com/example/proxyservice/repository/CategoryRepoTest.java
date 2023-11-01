package com.example.proxyservice.repository;

import com.example.proxyservice.models.Categories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CategoryRepoTest {
    @Autowired
    CategoryRepository categoryRepository;

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
    void updaterCategoryById(){
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


}
