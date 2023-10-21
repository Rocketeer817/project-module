package com.example.proxyservice.controllers;

import com.example.proxyservice.dtos.CategoryDto;
import com.example.proxyservice.dtos.ProductResponseDto;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.services.FakeStoreCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    private FakeStoreCategoryService fakeStoreCategoryService;
    public CategoryController(FakeStoreCategoryService fakeStoreCategoryService){
        this.fakeStoreCategoryService = fakeStoreCategoryService;
    }
    @GetMapping("")
    public List<String> getCategories(){

        return fakeStoreCategoryService.getCategories();
    }

    @GetMapping("/{categoryName}")
    public List<ProductResponseDto> getProductsOfSingleCategory(@PathVariable("categoryName") String categoryName){
        return fakeStoreCategoryService.getAllProductsOfSingleCategory(categoryName).stream().map(product -> getProductDto(product)).toList();
    }

    private ProductResponseDto getProductDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto(){
            {
                setId(product.getId());
                setTitle(product.getTitle());
                setPrice(product.getPrice());
                setDescription(product.getDescription());
                setImage(product.getImage());
                setCategory(product.getCategory().getName());
            }
        };
        return productResponseDto;
    }
}
