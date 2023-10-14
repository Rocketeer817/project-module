package com.example.proxyservice.controllers;

import com.example.proxyservice.dtos.CategoryDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @GetMapping("")
    public String getCategories(){
        return "categories";
    }

    @PostMapping("")
    public String addCategory(@RequestBody CategoryDto categoryDto){
        return "new category added "+ categoryDto;
    }

    @GetMapping("/{categoryName}")
    public String getProductsOfSingleCategory(@PathVariable("categoryName") String categoryName){
        return "single category " + categoryName;
    }

    @PutMapping("/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") Long categoryId){
        return "updating the category " + categoryId;
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId){
        return "delete the category" + categoryId;
    }
}
