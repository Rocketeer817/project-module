package com.example.proxyservice.controllers;

import com.example.proxyservice.dtos.ProductDto;
import com.example.proxyservice.services.IProductService;
import com.example.proxyservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }
    @GetMapping("")
    public String getProducts(){
        return "products";
    }

    @PostMapping("/")
    public String addProduct(@RequestBody ProductDto productDto){
        return "new product added "+productDto;
    }

    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "single product " + productId;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        return "updating the product "+ productDto;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "deletign the product " + productId;
    }

}
