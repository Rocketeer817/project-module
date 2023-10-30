package com.example.proxyservice.repository;

import com.example.proxyservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    //List<Product> getProductsOrderByPriceDesc();
    List<Product> findAllProductsByOrderByPriceDesc();

    List<Product> findAllByPriceBetweenOrderByPriceDesc(int start, int end);

    List<Product> deleteAllByPriceBetweenOrderByPriceDesc(int start, int end);

    List<Product> findAllByOrderByIdDesc();
}
