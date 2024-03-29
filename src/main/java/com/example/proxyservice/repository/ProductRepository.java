package com.example.proxyservice.repository;

import com.example.proxyservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByOrderByIdDesc();
    Page<Product> findByTitleEquals(String title, Pageable pageable);

    Page<Product> findByTitleContaining(String title, Pageable pageable);
}
