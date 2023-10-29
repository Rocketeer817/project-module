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
    void findCategoryName(){
       Categories category = categoryRepository.findById(1L).get();

        System.out.println("Debug");
    }
}
