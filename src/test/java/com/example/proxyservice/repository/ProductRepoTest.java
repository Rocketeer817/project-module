package com.example.proxyservice.repository;

import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductRepoTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Transactional
    //@Rollback(value = false)
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
    //@Rollback(value = false)
    void saveProductsAndCategory() {
        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories = categoryRepository.save(categories);

        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        product.setPrice(25000d);
        product.setCategory(categories);
        productRepository.save(product);

//        Categories categories1 = categoryRepository.findById(1L).get();
//        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }

    @Test
    @Transactional
    //@Rollback(value = false)
    void saveProductAndCategories2(){
        Categories categories = new Categories();
        categories.setName("Fashion");
        categories.setDescription("Fashion");
        categoryRepository.save(categories);

        Product product = new Product();
        product.setTitle("Strapless Dress");
        product.setCategory(categories);
        product.setDescription("Newest Exclusive Zara product");
        product.setPrice(1500);
        productRepository.save(product);

        System.out.println("Debug");
    }

    @Test
    @Transactional
    void increaseAllIsByOne(){
        List<Product> productsOld = productRepository.findAll();
        List<Product> updatedProducts = new ArrayList<>();
        for(Product product : productsOld){
            product.setPrice(product.getPrice()+1);
           Product updatedProduct  = productRepository.save(product);
           updatedProducts.add(updatedProduct);
        }

        System.out.println("Debug");
    }

    @Test
    @Transactional
    void getProductsInSortedOrder(){
        List<Product> products = productRepository.findAllProductsByOrderByPriceDesc();
        System.out.println("Debug");

        for(int i=1;i<products.size();i++){
            assert(products.get(i-1).getPrice()>=products.get(i).getPrice());
        }
    }

    @Test
    @Transactional
    void getProductsInPriceRange(){
        List<Product> products = productRepository.findAllByPriceBetweenOrderByPriceDesc(1000,2000);
        System.out.println("Debug");

        for(int i=1;i<products.size();i++){
            assert(products.get(i-1).getPrice()>=products.get(i).getPrice());
            assert(products.get(i).getPrice()>=1000);
            assert(products.get(i).getPrice()<=2000);
        }
    }

    @Test
    @Transactional
    void deleteProductsInPriceRange(){
        List<Product> products = productRepository.deleteAllByPriceBetweenOrderByPriceDesc(1000,2000);
        System.out.println("Debug");

        for(int i=1;i<products.size();i++){
            System.out.println(products.get(i));
            assert(products.get(i-1).getPrice()>=products.get(i).getPrice());
            assert(products.get(i).getPrice()>=1000);
            assert(products.get(i).getPrice()<=2000);
        }
    }

    @Test
    @Transactional
    void getProductsInDescendingOrder(){
        List<Product> products = productRepository.findAllByOrderByIdDesc();

        System.out.println(products.get(0));
        for(int i=1;i<products.size();i++){
            System.out.println(products.get(i));
            assert(products.get(i-1).getId()>=products.get(i).getId());
        }
    }





}
