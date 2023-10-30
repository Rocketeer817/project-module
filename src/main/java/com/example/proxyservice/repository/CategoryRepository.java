package com.example.proxyservice.repository;

import com.example.proxyservice.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long>  {
    public Categories findByName(String name);
}
