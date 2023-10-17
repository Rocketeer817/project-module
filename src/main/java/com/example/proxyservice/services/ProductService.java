package com.example.proxyservice.services;

import com.example.proxyservice.dtos.ProductDto;
import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public List<Product> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto[] productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : productDtos) {
            products.add(getProduct(productDto));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();
        System.out.println(productDto);
        return getProduct(productDto);
    }

    private Product getProduct(ProductDto productDto) {
        Product product =  new Product() {
            {
                setId(productDto.getId());
                setTitle(productDto.getTitle());
                setPrice(productDto.getPrice());
                setDescription(productDto.getDescription());
                setImage(productDto.getImage());
            }
        };

        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        return product;
    }


    public Product createProduct(ProductDto productDto) {
        Product product = getProduct(productDto);
        System.out.println("Product created with details - "+product);
        return product;
    }


    public Product updateProduct(Long id,ProductDto productDto) throws IllegalArgumentException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto1 = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();

        if(productDto1 == null){
            System.out.println("Product not found with id - "+id);
            throw new IllegalArgumentException("Product not found with id - "+id);
        }
        else {
            Product product = getProduct(productDto);
            System.out.println("Product replaced with details - "+product );
            return product;
        }

    }

    public Product replaceProduct(Long id, ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto1 = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();

        if(productDto1 == null){
            System.out.println("Product not found with id - "+id);
            return createProduct(productDto);
        }
        else {
            Product product = getProduct(productDto);
            System.out.println("Product replaced with details - "+product );
            return product;
        }

    }


    public Product deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();
        if(productDto == null){
            System.out.println("Product not found with id - "+id);
            throw new IllegalArgumentException("Product not found with id - "+id);
        }
        else {
            Product product = getProduct(productDto);
            System.out.println("Product deleted with details - "+product );
            return product;
        }
    }
}
