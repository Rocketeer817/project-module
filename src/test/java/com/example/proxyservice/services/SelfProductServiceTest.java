package com.example.proxyservice.services;

import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfProductServiceTest {

    @Autowired
    private SelfProductService selfProductService;

    @Autowired
    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("Test getProducts")
    //Happy Case
    public void test_getProducts_ReturnListOfProducts() {
        //Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product(){
            {
                setId(1L);
                setTitle("Iphone");
                setDescription("Exclusive Apple product");
                setPrice(1000);
            }
        });

        products.add(new Product(){
            {
                setId(2L);
                setTitle("Samsung");
                setDescription("Exclusive Samsung product");
                setPrice(1000);
            }
        });

        when(productRepository.findAll()).thenReturn(products);

        //Act
        List<Product> productList = selfProductService.getProducts();

        //Assert
        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertEquals("Iphone", productList.get(0).getTitle());
        assertEquals("Samsung", productList.get(1).getTitle());

    }

    @Test
    @DisplayName("Test getProducts when no products in db")
    //Edge case
    public void test_getAllProducts_when_no_products_in_db_return_empty_list() {
        //Arrange
        List<Product> products = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(products);

        //Act
        List<Product> productList = selfProductService.getProducts();

        //Assert
        assertNotNull(productList);
        assertEquals(0, productList.size());
    }

    @Test
    @DisplayName("Test getProducts when repository throws exception")
    //bad case
    public void test_getAllProducts_when_repository_throws_exception() {
        //Arrange
        when(productRepository.findAll()).thenThrow(new RuntimeException("Something went wrong"));

        //Act
//        try {
//            List<Product> productList = selfProductService.getProducts();
//            fail("Exception should be thrown");
//        } catch (Exception e) {
//            //Assert
//            assertEquals("Something went wrong", e.getMessage());
//        }

        assertThrows(RuntimeException.class, () -> {
            selfProductService.getProducts();
        });
    }



    @Test
    @DisplayName("Test getProductById")
    //Happy case
    void test_getProductById_return_a_product() {
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Iphone");
        product.setDescription("Exclusive Apple product");
        product.setPrice(1000);

        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(product));

        //Act
        Product product1 = selfProductService.getProductById(1L);

        //Assert
        assertNotNull(product1);
        assertEquals(product.getId(), product1.getId());
        assertEquals(product.getTitle(), product1.getTitle());
        assertEquals(product.getDescription(), product1.getDescription());
        assertEquals(product.getPrice(), product1.getPrice());

    }

    @Test
    @DisplayName("Test getProductById when no product found")
    //Edge case
    public void test_getProductById_when_no_product_found() {
        //Arrange
        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        //Act
        //Product product1 = selfProductService.getProductById(1L);

        //Assert
        assertThrows(NoSuchElementException.class, () -> {
            selfProductService.getProductById(1L);
        });
    }

    @Test
    @DisplayName("Test getProductById when repository throws exception")
    //Edge case
    public void test_getProductById_when_repository_throws_exception() {
        //Arrange
        when(productRepository.findById(any(Long.class))).thenThrow(new RuntimeException("Something went wrong"));

        //Assert
        assertThrows(RuntimeException.class, () -> {
            selfProductService.getProductById(1L);
        });

    }

    @Test
    @DisplayName("Test createProduct")
    //Happy case
    void test_createProduct_return_created_product() {
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Iphone");
        product.setDescription("Exclusive Apple product");
        product.setPrice(1000);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        //Act
        Product product1 = selfProductService.createProduct(product);

        //Assert
        assertNotNull(product1);
        assertEquals(product.getId(), product1.getId());
        assertEquals(product.getTitle(), product1.getTitle());
        assertEquals(product.getDescription(), product1.getDescription());
        assertEquals(product.getPrice(), product1.getPrice());
    }

    @Test
    @DisplayName("Test createProduct when inserted product is null")
    //Bad case
    public void test_createProduct_when_insertedProduct_is_null(){
        //Arrange
        Product product = null;
        when(productRepository.save(product)).thenThrow(InvalidDataAccessApiUsageException.class);

        //Assert
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            selfProductService.createProduct(product);
        });
    }

    @Test
    @DisplayName("Test createProduct when repository throws exception")
    //Edge case
    public void test_createProduct_when_repository_throws_exception(){
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Iphone");
        product.setDescription("Exclusive Apple product");
        product.setPrice(1000);

        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Something went wrong"));

        //Assert
        assertThrows(RuntimeException.class, () -> {
            selfProductService.createProduct(product);
        });
    }

    @Test
    @DisplayName("Test updateProduct when price is updated")
    //Happy case
    public void test_updateProduct_return_updated_product() {
        //Arrange
        Long l = 1L;

        Product originalproduct = new Product();
        originalproduct.setId(l);
        originalproduct.setTitle("Iphone");
        originalproduct.setDescription("Exclusive Apple product");
        originalproduct.setPrice(1000);

        Product updatedproduct = new Product();
        updatedproduct.setId(l);
        updatedproduct.setTitle("Iphone");
        updatedproduct.setDescription("Exclusive Apple product");
        updatedproduct.setPrice(2000);

        when(productRepository.findById(l)).thenReturn(java.util.Optional.of(originalproduct));
        when(productRepository.save(updatedproduct)).thenReturn(updatedproduct);

        Product product2 = new Product();
        product2.setPrice(2000);

        //Act
        Product product1 = selfProductService.updateProduct(l,product2);

        //Assert
        assertNotNull(product1);
        assertEquals(originalproduct.getId(), product1.getId());
        assertEquals(originalproduct.getTitle(), product1.getTitle());
        assertEquals(originalproduct.getDescription(), product1.getDescription());
        assertEquals(product2.getPrice(), product1.getPrice());
    }

    @Test
    @DisplayName("Test updateProduct when description is updated")
    void test_updateProduct_when_description_is_updated_returns_product_with_updated_description() {
        //Arrange
        Long l = 1L;

        Product originalproduct = new Product();
        originalproduct.setId(l);
        originalproduct.setTitle("Iphone");
        originalproduct.setDescription("Exclusive Apple product");
        originalproduct.setPrice(1000);

        Product updatedproduct = new Product();
        updatedproduct.setId(l);
        updatedproduct.setTitle("Iphone");
        updatedproduct.setDescription("Exclusive Apple product in 2023");
        updatedproduct.setPrice(1000);

        when(productRepository.findById(l)).thenReturn(java.util.Optional.of(originalproduct));
        when(productRepository.save(updatedproduct)).thenReturn(updatedproduct);

        Product product2 = new Product();
        product2.setDescription("Exclusive Apple product in 2023");

        //Act
        Product product1 = selfProductService.updateProduct(l,product2);

        //Assert
        assertNotNull(product1);
        assertEquals(originalproduct.getId(), product1.getId());
        assertEquals(originalproduct.getTitle(), product1.getTitle());
        assertEquals(product2.getDescription(), product1.getDescription());
        assertEquals(originalproduct.getPrice(), product1.getPrice());
    }

    @Test
    @DisplayName("Test updateProduct when title is updated")
    public void test_updateProduct_when_title_updated_return_product_with_updated_title(){
        //Act
        Long l = 1L;

        Product originalproduct = new Product();
        originalproduct.setId(l);
        originalproduct.setTitle("Iphone");
        originalproduct.setDescription("Exclusive Apple product");
        originalproduct.setPrice(1000);

        Product updatedproduct = new Product();
        updatedproduct.setId(l);
        updatedproduct.setTitle("Iphone 23");
        updatedproduct.setDescription("Exclusive Apple product");
        updatedproduct.setPrice(1000);

        when(productRepository.findById(l)).thenReturn(java.util.Optional.of(originalproduct));
        when(productRepository.save(updatedproduct)).thenReturn(updatedproduct);

        Product product2 = new Product();
        product2.setTitle("Iphone 23");

        //Act
        Product product1 = selfProductService.updateProduct(l,product2);

        //Assert
        assertNotNull(product1);
        assertEquals(originalproduct.getId(), product1.getId());
        assertEquals(product2.getTitle(), product1.getTitle());
        assertEquals(originalproduct.getDescription(), product1.getDescription());
        assertEquals(originalproduct.getPrice(), product1.getPrice());

    }

    @Test
    @DisplayName("Test updateProduct when image is updated")
    public void test_updateProduct_when_image_updated_return_product_with_updated_image() {
        //Act
        Long l = 1L;

        Product originalproduct = new Product();
        originalproduct.setId(l);
        originalproduct.setTitle("Iphone");
        originalproduct.setDescription("Exclusive Apple product");
        originalproduct.setPrice(1000);
        originalproduct.setImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.apple.com%2Fin%2Fiphone%2F&psig=AOvVaw1QZ3Z4Q4Z4Q4Z4Q4Z4Q4Z4&ust=1629789850008000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJjQ4Z3Q5vICFQAAAAAdAAAAABAD");

        Product updatedproduct = new Product();
        updatedproduct.setId(l);
        updatedproduct.setTitle("Iphone");
        updatedproduct.setDescription("Exclusive Apple product");
        updatedproduct.setPrice(1000);
        updatedproduct.setImage("abc");

        when(productRepository.findById(l)).thenReturn(java.util.Optional.of(originalproduct));
        when(productRepository.save(updatedproduct)).thenReturn(updatedproduct);

        Product product2 = new Product();
        product2.setImage("abc");

        //Act
        Product product1 = selfProductService.updateProduct(l,product2);

        //Assert
        assertNotNull(product1);
        assertEquals(originalproduct.getId(), product1.getId());
        assertEquals(originalproduct.getTitle(), product1.getTitle());
        assertEquals(originalproduct.getDescription(), product1.getDescription());
        assertEquals(originalproduct.getPrice(), product1.getPrice());
        assertEquals(product2.getImage(), product1.getImage());

    }

    @Test
    @DisplayName("Test updateProduct when category is updated")
    public void test_updateProduct_when_category_updated_return_product_with_updated_category() {
        //Act
        Long l = 1L;

        Categories originalCategory = new Categories();
        originalCategory.setName("Electronics");

        Product originalproduct = new Product();
        originalproduct.setId(l);
        originalproduct.setTitle("Iphone");
        originalproduct.setDescription("Exclusive Apple product");
        originalproduct.setPrice(1000);

        originalCategory.setProductList(new ArrayList<>());
        originalCategory.getProductList().add(originalproduct);

        originalproduct.setCategory(originalCategory);

        Categories updateCategory = new Categories();
        updateCategory.setName("Mobiles");

        Product updatedproduct = new Product();
        updatedproduct.setId(l);
        updatedproduct.setTitle("Iphone");
        updatedproduct.setDescription("Exclusive Apple product");
        updatedproduct.setPrice(1000);

        updateCategory.setProductList(new ArrayList<>());
        updateCategory.getProductList().add(updatedproduct);



        when(productRepository.findById(l)).thenReturn(java.util.Optional.of(originalproduct));
        when(productRepository.save(updatedproduct)).thenReturn(updatedproduct);

        Product product2 = new Product();
        product2.setCategory(null);

        //Act
        Product product1 = selfProductService.updateProduct(l,product2);

        //Assert
        assertNotNull(product1);
        assertEquals(originalproduct.getId(), product1.getId());
        assertEquals(originalproduct.getTitle(), product1.getTitle());
        assertEquals(originalproduct.getDescription(), product1.getDescription());
        assertEquals(originalproduct.getPrice(), product1.getPrice());
        assertEquals(originalproduct.getCategory().getName(), product1.getCategory().getName());

    }

    @Test
    void replaceProduct() {

    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getAllProductsSortedById() {
    }
}